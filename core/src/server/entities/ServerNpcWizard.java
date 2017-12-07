package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import server.ServerGameMap;
import server.skills.ServerShootFireBall;
/**
 * wizard npc
 * @author mauricio
 *
 */
public class ServerNpcWizard extends ServerNpc {
	
	public ServerNpcWizard(ServerGameMap gameMap, Body body) {
		super(gameMap, body, CharacterType.WIZARD);
		this.skills[0] = new ServerShootFireBall(this, gameMap);
		this.attackFrequency *= 120f;
		this.attackDistance = 10f;
	}

}
