package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootFireBall;
/**
 * wizard
 * @author mauricio
 *
 */
public class ServerWizard extends ServerPlayer {

	public ServerWizard(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.WIZARD);
		this.skills[0] = new ServerShootFireBall(this, gameMap);
	}

}
