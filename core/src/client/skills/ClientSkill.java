package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;

/**
 * Skill super class
 * 
 * @author mauricio
 *
 */
public abstract class ClientSkill {

	/**
	 * position
	 */
	public float x;
	/**
	 * position
	 */
	public float y;
	/**
	 * dirction
	 */
	public float direction;
	/**
	 * typpe of skill
	 */
	public SkillType type;
	
	/**
	 * @param x pos
	 * @param y pos
	 * @param direction dir
	 * @param type type
	 */
	public ClientSkill(float x, float y, float direction, SkillType type) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.type = type;
	}
	
	/**
	 * updates the skills position
	 * 
	 * @param ss
	 */
	public abstract void update(SkillState ss);
	
	/**
	 * @return
	 */
	public abstract Sprite getFrame();	
}
