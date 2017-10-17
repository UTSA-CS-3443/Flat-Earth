package utilities;

/**
 * this class is set by the Controller/logic handler
 * looks really similar for now to keyboard state but will eventually hold way more things,
 * like enemy positions
 * @author mauricio
 *
 */


public class GameState {
	
	private boolean charMovement = false;
	private float charForceX = 0, charForceY = 0, charDirection = 0;
	
	public GameState() {}
	
	public void setCharStats(boolean movement, float forceX, float forceY, float direction) {
		this.charMovement = movement;
		this.charForceX = forceX;
		this.charForceY = forceY;
		this.charDirection = direction;
	}

	// all of these must be called in a synchronized block
	public boolean charMovement() {return charMovement;}

	public float getCharDirection() {return charDirection;}

	public float getCharForceX() {return charForceX;}

	public float getCharForceY() {return charForceY;}
	
	public void updateData(GameState igs) {
		this.charMovement = igs.charMovement();
		this.charForceX = igs.getCharForceX();
		this.charForceY = igs.getCharForceY();
		this.charDirection = igs.getCharDirection();
	}
}








