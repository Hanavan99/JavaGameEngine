package com.github.hanavan99.javagameengine.game;

import com.github.hanavan99.javagameengine.net.Connection;
import com.github.hanavan99.javagameengine.net.net2.DataTypeManager;
import com.github.hanavan99.javagameengine.net.net2.PacketManager;
import com.github.hanavan99.javagameengine.player.Player;

public abstract class Game<P extends Player, GI extends GameInstance> {

	public abstract String getName();

	public abstract P createPlayer(Connection conn);

	public abstract GI createGameInstance();

	public abstract DataTypeManager getDataTypeManager();

	public abstract PacketManager getPacketManager();

}
