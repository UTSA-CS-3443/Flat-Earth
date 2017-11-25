package client.skills;

import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.SkillState;
import server.skills.SkillType;

public abstract class ClientSkill {

	public float x;
	public float y;
	public float direction;
	public SkillType type;
	
	public ClientSkill(float x, float y, float direction, SkillType type) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.type = type;
	}
	
	public abstract void update(SkillState ss);
	
	public abstract Sprite getFrame();	
}
