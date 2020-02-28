package com.github.hanavan99.javagameengine.player;

import com.github.hanavan99.javagameengine.net.Connection;

public abstract class Player {

	private final Connection connection;

	public Player(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
