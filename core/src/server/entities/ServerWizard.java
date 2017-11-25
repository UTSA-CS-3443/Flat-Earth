package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;

public class ServerWizard extends ServerPlayer {

	public ServerWizard(ServerGameMap gameMap, Body body) {
		super(gameMap, body);
	}

}
