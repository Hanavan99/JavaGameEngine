package com.github.hanavan99.javagameengine.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This interface is used to handle reading and writing of custom data types.
 * 
 * @author Hanavan Kuhn
 *
 * @param <T> the type of the data that is handled
 */
public interface DataTypeHandler<T> {

	public T read(DataInputStream in, NetworkManager manager) throws IOException;

	public void write(T t, DataOutputStream out, NetworkManager manager) throws IOException;

}
