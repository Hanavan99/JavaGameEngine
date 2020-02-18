package com.github.hanavan99.javagameengine.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class NetworkManager {

	private HashMap<Class<?>, DataTypeHandler<?>> dataTypeHandlers = new HashMap<Class<?>, DataTypeHandler<?>>();
	private HashMap<Integer, GamePacketHandler<?>> gamePacketHandlers = new HashMap<Integer, GamePacketHandler<?>>();
	private int currentPacketID = 0;

	public void registerPacketHandler(GamePacketHandler<?> handler) {
		gamePacketHandlers.put(currentPacketID++, handler);
	}

	public <T> void registerDataTypeHandler(Class<T> type, DataTypeHandler<T> handler) {
		dataTypeHandlers.put(type, handler);
	}

	public GamePacket read(DataInputStream in) throws IOException {
		int packetID = in.readInt();
		GamePacketHandler<?> handler = gamePacketHandlers.get(packetID);
		if (handler != null) {
			GamePacket p = handler.createPacket();
			p.read(in, this);
			return p;
		}
		throw new IllegalArgumentException(
				"recieved packet with ID " + packetID + " but no GamePacketHandler exists for it");
	}

	public void write(DataOutputStream out, GamePacket packet) throws IOException {
		int packetID = -1;
		for (Entry<Integer, GamePacketHandler<?>> entry : gamePacketHandlers.entrySet()) {
			if (entry.getValue().getPacketClass().equals(packet.getClass())) {
				packetID = entry.getKey();
				break;
			}
		}
		if (packetID != -1) {
			out.writeInt(packetID);
			packet.write(out, this);
		}
		throw new IllegalArgumentException(
				"no GamePacketHandler exists for packet class " + packet.getClass().toString());
	}

	@SuppressWarnings("unchecked")
	public <T> T read(DataInputStream in, Class<T> type) throws IOException {
		DataTypeHandler<T> handler = (DataTypeHandler<T>) dataTypeHandlers.get(type);
		if (handler != null) {
			return handler.read(in, this);
		}
		throw new IllegalArgumentException("no DataTypeHandler was found for type " + type.toString());
	}

	@SuppressWarnings("unchecked")
	public <T> void write(DataOutputStream out, Class<T> type, T object) throws IOException {
		DataTypeHandler<T> handler = (DataTypeHandler<T>) dataTypeHandlers.get(type);
		if (handler != null) {
			handler.write(object, out, this);
		}
		throw new IllegalArgumentException("no DataTypeHandler was found for type " + type.toString());
	}

}
