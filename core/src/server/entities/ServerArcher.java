package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootArrow;

/**
 * archer
 * 
 * @author mauricio
 *
 */
public class ServerArcher extends ServerPlayer {

	/**
	 * @param gameMap map
	 * @param body physics
	 */
	public ServerArcher(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.ARCHER);
		this.skills[0] = new ServerShootArrow(this, gameMap);
	}

}
