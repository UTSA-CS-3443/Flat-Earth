package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootArrow;
import server.skills.ServerShootFireBall;

public class ServerWizard extends ServerPlayer {

	public ServerWizard(ServerGameMap gameMap, Body body) {
		super(gameMap, body);
		this.skills[0] = new ServerShootFireBall(this, gameMap);
	}

}
