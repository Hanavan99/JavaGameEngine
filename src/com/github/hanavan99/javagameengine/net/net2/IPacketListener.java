package com.github.hanavan99.javagameengine.net.net2;

import java.io.IOException;

import com.github.hanavan99.javagameengine.game.Game;

public interface IPacketListener<G extends Game<?, ?>, P extends Packet> {

	public void packetRecieved(Connection<G> connection, P packet) throws IOException;

}
