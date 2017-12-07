package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootArrow;

public class ServerNpcArcher extends ServerNpc {

	public ServerNpcArcher(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.ARCHER);
		this.skills[0] = new ServerShootArrow(this, gameMap);
		this.attackFrequency *= 120f;
		this.attackDistance = 15f;
	}

}
