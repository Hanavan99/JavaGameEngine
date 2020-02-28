package com.github.hanavan99.javagameengine.net.net2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.github.hanavan99.javagameengine.game.Game;

public class PacketManager {

	private HashMap<Class<? extends Packet>, IPacketHandler<?>> packetMap = new HashMap<Class<? extends Packet>, IPacketHandler<?>>();
	private DataTypeManager dataTypeManager;

	public PacketManager(Game<?, ?> game) {
		dataTypeManager = game.getDataTypeManager();
	}

	public <T extends Packet> void registerPacketHandler(Class<T> packetClass, IPacketHandler<T> handler) {
		packetMap.put(packetClass, handler);
	}

	@SuppressWarnings("unchecked")
	public Packet readPacket(DataInputStream in) throws IOException {
		String packetClassName = in.readUTF();
		try {
			Class<? extends Packet> packetClass = (Class<? extends Packet>) Class.forName(packetClassName);
			IPacketHandler<?> handler = packetMap.get(packetClass);
			if (handler != null) {
				return handler.readPacket(in, dataTypeManager);
			} else {
				throw new IllegalStateException("no packet handler for packet " + packetClass.toString() + " registered");
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("packet class " + packetClassName + " does not exist in classpath", e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Packet> void writePacket(DataOutputStream out, T packet) throws IOException {
		Class<? extends Packet> packetClass = packet.getClass();
		out.writeUTF(packetClass.toString());
		IPacketHandler<T> handler = (IPacketHandler<T>) packetMap.get(packetClass);
		if (handler != null) {
			handler.writePacket(out, dataTypeManager, packet);
		} else {
			throw new IllegalStateException("no packet handler for packet " + packetClass.toString() + " registered");
		}
	}

}
