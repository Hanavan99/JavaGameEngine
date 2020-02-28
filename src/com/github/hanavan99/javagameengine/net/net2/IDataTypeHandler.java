package com.github.hanavan99.javagameengine.net.net2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IDataTypeHandler<T> {

	public T read(DataInputStream in, DataTypeManager dataTypeManager) throws IOException;

	public void write(DataOutputStream out, DataTypeManager dataTypeManager, T object) throws IOException;

}
