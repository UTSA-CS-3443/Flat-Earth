package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.CharacterState;
import utilities.Sys;

public class ServerNpc extends ServerCharacter {

	public ServerNpc(Body body) {
		super(body);
	}
	
	public void update(Vector2 vectors[]) {
		
		float forceX = 0;
		float forceY = 0;
		int left = 0, right = 0, up = 0, down = 0;
		/* Create vector for aggro target. */
		Vector2 closest = vectors[0];
		/* Find closest aggro target by cycling through vectors[]. */
		for(Vector2 target: vectors)
			if ((Math.abs(target.x - this.body.getPosition().x) +
				 Math.abs(target.y - this.body.getPosition().y) <
				 Math.abs(closest.x - this.body.getPosition().x) +
				 Math.abs(closest.y - this.body.getPosition().y)))
				closest = target;

		float distance = (float) Math.sqrt(Math.pow((closest.x - this.body.getPosition().x), 2)
								          -Math.pow((closest.y - this.body.getPosition().y), 2));

		/* Test if closest target is within aggro range. Moves if so. */
		if (/*distance <= 30f*/ true)
		{
			double angle;
			/* Calculate angle. */
			angle = Math.atan2(closest.y - this.body.getPosition().y, closest.x - this.body.getPosition().x);
			// had to fix some issues with the Math.atan2 function returning bogus numbers
			if ((int)closest.x - (int)this.body.getPosition().x == 0) {
				forceX = 0;
			} else if (Math.cos(angle) > 0f) {
				forceX =  .5f * FORCE_CONSTANT;
				right = 1;
			} else if (Math.cos(angle) < 0f) {
				forceX = -(.5f * FORCE_CONSTANT);
				left = 1;
			}
			if ((int)closest.y - (int)this.body.getPosition().y == 0) {
				forceY = 0;
			} else if (Math.sin(angle) > 0f) {
				forceY =  .5f * FORCE_CONSTANT;
				up = 1;
			} else if (Math.sin(angle) < 0f) {
				forceY = -(.5f * FORCE_CONSTANT);
				down = 1;
			}
		}

		/* Attack distance check */
		if (distance <= 2f)
			attack();

		/* Modulated code for possible future implementation of a lower-end aggro threshold. */
		if (distance <= 1f)
			forceX = forceY = 0;
		// only change the direction if some sort of movement is made
		// this fixes some bug where if no movemnt is made, the stand frame will default to north-east
		// direction is used by client for deciding what animation frame to use
		this.movement = !(forceX == 0 && forceY == 0);
		if (this.movement) 
			this.direction = DIRECTIONS[(-down + up)+1][(-left + right)+1];
		//Sys.print("" + this.direction);
		//this.body.applyForceToCenter(forceX, forceY, true);
	}
	
	public void attack() {
		
		//TODO obviously 
		// spawn some attack spawn, probably add that functionality to the spawner
		
	}
	
	@Override
	public CharacterState getState() {
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction, this.health);
	}
	
}
