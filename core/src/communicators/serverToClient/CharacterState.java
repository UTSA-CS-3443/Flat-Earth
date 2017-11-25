package communicators.serverToClient;

import utilities.ActionTrigger;

public class CharacterState {
	
	public int id;
	
	public boolean charMovement;
	public float x = 0, y = 0, charDirection = 0;
	
	public int health;
	
	public ActionTrigger state = ActionTrigger.NORMAL;
	
	public CharacterState() {}
	
	public CharacterState(boolean movement, float fx, float fy, float direction, int health, ActionTrigger state) {
		this.set(movement, fx, fy, direction, health, state);
	}
	
	// two different ways to set the state, this one probably never used
	public void set(boolean movement, float fx, float fy, float direction, int health, ActionTrigger state) {
		charMovement = movement;
		x = fx;
		y = fy;
		charDirection = direction;
		this.health = health;
		this.state = state;
	}
	// only way to set the state so far
	public void set(CharacterState cs) {
		this.set(cs.charMovement, cs.x, cs.y, cs.charDirection, cs.health, cs.state);
	}
	
	
	// all of these must be called in a synchronized block
	public boolean getMovement() {return charMovement;}

	public float getDirection() {return charDirection;}

	public float getX() {return x;}

	public float getY() {return y;}
	
	public int getHealth() {return health;}
	
	public ActionTrigger getState() { return this.state; }
	
	public String toString() {
		return "" + this.x + " " + this.y;
	}
	
}
