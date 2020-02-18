package com.github.hanavan99.javagameengine.net;

public interface GamePacketHandler<T extends GamePacket> {

	public T createPacket();

	public Class<T> getPacketClass();

}
