package com.github.hanavan99.javagameengine.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Connection {

	private final Socket socket;
	private final DataInputStream in;
	private final DataOutputStream out;
	private final NetworkManager manager;
	private final ArrayList<GamePacket> writePackets = new ArrayList<GamePacket>();
	private final HashMap<Class<? extends GamePacket>, GamePacketListener<?>> listeners = new HashMap<Class<? extends GamePacket>, GamePacketListener<?>>();
	private Thread readThread;
	private Thread writeThread;
	private boolean running = false;

	public Connection(Socket socket, NetworkManager manager) throws IOException {
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		this.manager = manager;

	}

	public void open() throws IOException {
		running = true;
		readThread = new Thread(new ReadRunnable());
		writeThread = new Thread(new WriteRunnable());
		readThread.start();
		writeThread.start();
	}

	public void close() throws IOException {
		running = false;
		readThread.interrupt();
		writeThread.interrupt();
	}

	public <T extends GamePacket> void registerPacketListener(Class<T> packetClass, GamePacketListener<T> listener) {
		listeners.put(packetClass, listener);
	}

	public void writePacket(GamePacket packet) {
		synchronized (writePackets) {
			writePackets.add(packet);
		}
		writeThread.interrupt();
	}

	private final class ReadRunnable implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			while (running) {
				try {
					GamePacket packet = manager.read(in);
					GamePacketListener<GamePacket> listener = (GamePacketListener<GamePacket>) listeners
							.get(packet.getClass());
					if (listener != null) {
						listener.packetRecieved(packet);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private final class WriteRunnable implements Runnable {

		@Override
		public void run() {
			while (running) {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					// don't do anything
				}

				synchronized (writePackets) {
					while (writePackets.size() > 0) {
						try {
							GamePacket packet = writePackets.remove(0);
							packet.write(out, manager);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

}
