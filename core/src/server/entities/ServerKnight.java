package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerSlash;

public class ServerKnight extends ServerPlayer {

	public ServerKnight(ServerGameMap gameMap, Body body) {
		super(gameMap, body);
		this.skills[0] = new ServerSlash(this, gameMap);
	}

}
