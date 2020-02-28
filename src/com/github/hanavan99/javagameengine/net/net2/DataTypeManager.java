package com.github.hanavan99.javagameengine.net.net2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class DataTypeManager {

	private HashMap<Class<?>, IDataTypeHandler<?>> dataTypeMap = new HashMap<Class<?>, IDataTypeHandler<?>>();

	public <T> void registerDataTypeHandler(Class<? extends T> dataTypeClass, IDataTypeHandler<T> handler) {
		dataTypeMap.put(dataTypeClass, handler);
	}

	@SuppressWarnings("unchecked")
	public <T> T read(DataInputStream in, Class<T> dataTypeClass) throws IOException {
		IDataTypeHandler<T> handler = (IDataTypeHandler<T>) dataTypeMap.get(dataTypeClass);
		if (handler != null) {
			return handler.read(in, this);
		} else {
			throw new IllegalStateException("no data type handler for class " + dataTypeClass.toString() + " registered");
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void write(DataOutputStream out, Class<T> dataTypeClass, T object) throws IOException {
		IDataTypeHandler<T> handler = (IDataTypeHandler<T>) dataTypeMap.get(dataTypeClass);
		if (handler != null) {
			handler.write(out, this, object);
		} else {
			throw new IllegalStateException("no data type handler for class " + dataTypeClass.toString() + " registered");
		}
	}

}
