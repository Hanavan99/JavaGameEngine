package com.github.hanavan99.javagameengine.net.net2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IPacketHandler<T extends Packet> {

	public T readPacket(DataInputStream in, DataTypeManager dataTypeManager) throws IOException;

	public void writePacket(DataOutputStream out, DataTypeManager dataTypeManager, T packet) throws IOException;

}
