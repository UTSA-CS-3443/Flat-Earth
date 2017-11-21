package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.CharacterState;
import utilities.Sys;



public class ServerPlayer extends ServerCharacter {
	
	public ServerPlayer(Body body) {
		super(body);
		this.direction = 270; // direction to start off should be looking down
	}
	
	public void update(KeysPressed pressed) {
		float forceX = (pressed.left * -FORCE_CONSTANT) + (pressed.right *FORCE_CONSTANT);
		float forceY = (pressed.up * FORCE_CONSTANT) + (pressed.down * -FORCE_CONSTANT);
		this.movement = !(forceX == 0 && forceY == 0);
		// only change the direction if some sort of movement is made
		// this fixes some bug where if no movemnt is made, the stand frame will default to north-east
		// direction is used by client for deciding what animation frame to use
		if (this.movement) 
			this.direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
		this.body.applyForceToCenter(forceX, forceY, true);
	}
	
	@Override
	public CharacterState getState() {
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction, this.health, this.state);
	}
	
}
