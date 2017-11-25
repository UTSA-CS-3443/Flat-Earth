package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;
import utilities.Sys;

public class ClientSlash extends ClientSkill {

	public ClientSlash(float x, float y, float direction) {
		super(x, y, direction, SkillType.SLASH);
	}

	public ClientSlash(SkillState ss) {
		super(ss.x, ss.y, ss.direction, SkillType.SLASH);
	}
	
	@Override
	public void update(SkillState ss) {
		// does nothing since these classes are mainly for free-forming skills, like fireballs and arrows.
		// still, just keep it here for consistency
	}

	@Override
	public Sprite getFrame() {
		Sys.exit("this should never really be called");
		return null;
	}

}
