package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootArrow;
import server.skills.ServerSlash;

public class ServerArcher extends ServerPlayer {

	public ServerArcher(ServerGameMap gameMap, Body body) {
		super(gameMap, body);
		this.skills[0] = new ServerShootArrow(this, gameMap);
	}

}
