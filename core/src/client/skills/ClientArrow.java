package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;

/**
 * managers arrow
 * 
 * @author mauricio
 *
 */
public class ClientArrow extends ClientSkill {

	/**
	 * arrow sprite
	 */
	Sprite sprite;
	
	/**
	 * @param x pos
	 * @param y pos
	 * @param direction direction
	 */
	public ClientArrow(float x, float y, float direction) {
		super(x, y, direction, SkillType.ARROW);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param ss
	 */
	public ClientArrow(SkillState ss) {
		super(ss.x, ss.y, ss.direction, SkillType.ARROW);
	}

	/* (non-Javadoc)
	 * @see client.skills.ClientSkill#update(communicators.serverToClient.SkillState)
	 */
	@Override
	public void update(SkillState ss) {
		this.x = ss.x;
		this.y = ss.y;
		this.direction = ss.direction;
	}
	
	/* (non-Javadoc)
	 * @see client.skills.ClientSkill#getFrame()
	 */
	@Override
	public Sprite getFrame() {
		Sprite s = ClientSkillAnimations.getArrow();
		// set the rotation
		//s.setRotation(this.direction);
		s.setOrigin(0, 0);
		s.setRotation(this.direction);
		s.setPosition(this.x, this.y);
		return s;
	}
	
}
