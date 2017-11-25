package communicators.serverToClient;

import server.skills.SkillType;

public class SkillState {

	public float x;
	public float y;
	public float direction;
	public SkillType type;
	
	public SkillState(float x, float y, float direction, SkillType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
	}

	public String toString () {
		return "" + x + " " + y + " " + direction + " " + type;
	}
	
}
