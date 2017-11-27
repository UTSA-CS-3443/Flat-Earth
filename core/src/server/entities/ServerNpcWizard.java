package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootFireBall;
import utilities.Sys;

public class ServerNpcWizard extends ServerNpc {
	
	public ServerNpcWizard(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.WIZARD);
		this.skills[0] = new ServerShootFireBall(this, gameMap);
		this.attackFrequency *= 1.3f;
		this.attackDistance = 10f;
	}

}
