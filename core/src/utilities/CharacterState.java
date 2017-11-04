package utilities;



public class CharacterState {
	
	public int id;
	
	public boolean charMovement;
	public float charForceX = 0, charForceY = 0, charDirection = 0;
	
	public CharacterState() {}
	
	public CharacterState(boolean movement, float fx, float fy, float direction) {
		charMovement = movement;
		charForceX = fx;
		charForceY = fy;
		charDirection = direction;
	}
	
	// two different ways to set the state, this one probably never used
	public void set(boolean movement, float fx, float fy, float direction) {
		charMovement = movement;
		charForceX = fx;
		charForceY = fy;
		charDirection = direction;
	}
	// only way to set the state so far
	public void set(CharacterState cs) {
		charMovement = cs.charMovement;
		charForceX = cs.charForceX;
		charForceY = cs.charForceY;
		charDirection = cs.charDirection;
	}
	
	
	// all of these must be called in a synchronized block
	public boolean charMovement() {return charMovement;}

	public float getCharDirection() {return charDirection;}

	public float getCharForceX() {return charForceX;}

	public float getCharForceY() {return charForceY;}
	
}
