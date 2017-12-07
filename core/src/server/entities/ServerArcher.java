package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootArrow;

public class ServerArcher extends ServerPlayer {

	public ServerArcher(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.ARCHER);
		this.skills[0] = new ServerShootArrow(this, gameMap);
	}

}
