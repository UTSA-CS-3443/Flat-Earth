package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerSlash;

/**
 * knight 
 * 
 * @author mauricio
 *
 */
public class ServerKnight extends ServerPlayer {

	/**
	 * @param gameMap map of self
	 * @param body physics
	 */
	public ServerKnight(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.KNIGHT);
		this.skills[0] = new ServerSlash(this, gameMap);
	}

}
