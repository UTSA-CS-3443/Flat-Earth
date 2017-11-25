package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;
import utilities.Sys;

public class ClientFireBall extends ClientSkill {

	public ClientFireBall(float x, float y, float direction) {
		super(x, y, direction, SkillType.FIREBALL);
		// TODO Auto-generated constructor stub
	}
	
	public ClientFireBall(SkillState ss) {
		super(ss.x, ss.y, ss.direction, SkillType.FIREBALL);
	}
	
	@Override
	public void update(SkillState ss) {
		Sys.exit("ClientFireBall.update() : not doing anything");
	}
	
	@Override
	public Sprite getFrame() {
		// just a static fireball for now
		// should eventually get from an animation array or something
		Sprite s = ClientSkillAnimations.getFireBall();
		// set the rotation
		s.setPosition(this.x, this.y);
		return s;
	}
	
}
