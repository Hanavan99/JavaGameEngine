package com.github.hanavan99.javagameengine.net.net2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import com.github.hanavan99.javagameengine.game.Game;

public class Connection<G extends Game<?, ?>> {

	private final InetSocketAddress address;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private PacketManager packetManager;
	private boolean running = false;
	private Thread readThread;
	private ArrayList<ListenerListEntry<?>> packetListeners = new ArrayList<ListenerListEntry<?>>();

	public Connection(InetSocketAddress address, G game) throws IOException {
		this.address = address;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		packetManager = game.getPacketManager();

	}

	@SuppressWarnings("unchecked")
	private void packetHandler() {
		try {
			while (running) {
				Packet packet = packetManager.readPacket(in);
				for (ListenerListEntry<?> listenerEntry : packetListeners) {
					if (listenerEntry.getPacketClass().isInstance(packet)) {
						((IPacketListener<G, Packet>) listenerEntry.getListener()).packetRecieved(this, packet);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void connect() throws IOException {
		socket = new Socket(address.getAddress(), address.getPort());
		running = true;
		readThread = new Thread(this::packetHandler);
		readThread.start();
	}

	public void disconnect() throws IOException {
		running = false;
		readThread.interrupt();
		socket.close();
	}
	
	public void writePacket(Packet packet) throws IOException {
		packetManager.writePacket(out, packet);
	}

	public <T extends Packet> void registerPacketListener(Class<T> packetClass, IPacketListener<G, T> listener) {
		synchronized (packetListeners) {
			packetListeners.add(new ListenerListEntry<T>(packetClass, listener));
		}
	}

	private final class ListenerListEntry<T extends Packet> {

		private Class<T> packetClass;
		private IPacketListener<G, T> listener;

		private ListenerListEntry(Class<T> packetClass, IPacketListener<G, T> listener) {
			this.packetClass = packetClass;
			this.listener = listener;
		}

		public Class<T> getPacketClass() {
			return packetClass;
		}

		public void setPacketClass(Class<T> packetClass) {
			this.packetClass = packetClass;
		}

		public IPacketListener<G, T> getListener() {
			return listener;
		}

		public void setListener(IPacketListener<G, T> listener) {
			this.listener = listener;
		}

	}

}
