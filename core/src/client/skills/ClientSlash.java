package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;
import utilities.Sys;

/**
 * Slash for the knight
 * Doesnt actually do anything, just here for consistency
 * @author mauricio
 *
 */
public class ClientSlash extends ClientSkill {

	/**
	 * @param x postiion
	 * @param y position
	 * @param direction direction 
	 */
	public ClientSlash(float x, float y, float direction) {
		super(x, y, direction, SkillType.SLASH);
	}

	/**
	 * @param ss
	 */
	public ClientSlash(SkillState ss) {
		super(ss.x, ss.y, ss.direction, SkillType.SLASH);
	}
	
	/* (non-Javadoc)
	 * @see client.skills.ClientSkill#update(communicators.serverToClient.SkillState)
	 */
	@Override
	public void update(SkillState ss) {
		// does nothing since these classes are mainly for free-forming skills, like fireballs and arrows.
		// still, just keep it here for consistency
	}

	/* (non-Javadoc)
	 * @see client.skills.ClientSkill#getFrame()
	 */
	@Override
	public Sprite getFrame() {
		Sys.exit("this should never really be called");
		return null;
	}

}
