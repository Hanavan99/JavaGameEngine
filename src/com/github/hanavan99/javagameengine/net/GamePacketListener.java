package com.github.hanavan99.javagameengine.net;

public interface GamePacketListener<T extends GamePacket> {

	public void packetRecieved(T packet);

}
