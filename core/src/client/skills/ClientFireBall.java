package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;


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
		this.x = ss.x-.1f;
		this.y = ss.y;
		this.direction = ss.direction;
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
