package communicators.serverToClient;



public class CharacterState {
	
	public int id;
	
	public boolean charMovement;
	public float x = 0, y = 0, charDirection = 0;
	
	public CharacterState() {}
	
	public CharacterState(boolean movement, float fx, float fy, float direction) {
		charMovement = movement;
		x = fx;
		y = fy;
		charDirection = direction;
	}
	
	// two different ways to set the state, this one probably never used
	public void set(boolean movement, float fx, float fy, float direction) {
		charMovement = movement;
		x = fx;
		y = fy;
		charDirection = direction;
	}
	// only way to set the state so far
	public void set(CharacterState cs) {
		charMovement = cs.charMovement;
		x = cs.x;
		y = cs.y;
		charDirection = cs.charDirection;
	}
	
	
	// all of these must be called in a synchronized block
	public boolean getMovement() {return charMovement;}

	public float getDirection() {return charDirection;}

	public float getX() {return x;}

	public float getY() {return y;}
	
}
