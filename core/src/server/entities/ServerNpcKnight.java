package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerSlash;

public class ServerNpcKnight extends ServerNpc {

	public ServerNpcKnight(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.KNIGHT);
		this.skills[0] = new ServerSlash(this, gameMap);
		this.attackFrequency *= 120f;
		this.attackDistance = 2f;
	}

}
