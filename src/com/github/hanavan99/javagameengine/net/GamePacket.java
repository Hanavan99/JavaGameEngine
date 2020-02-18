package com.github.hanavan99.javagameengine.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Represents a packet of data that can be sent or received over the network.
 * 
 * @author Hanavan Kuhn
 *
 */
public abstract class GamePacket {

	public abstract void read(DataInputStream in, NetworkManager manager) throws IOException;

	public abstract void write(DataOutputStream out, NetworkManager manager) throws IOException;

}
