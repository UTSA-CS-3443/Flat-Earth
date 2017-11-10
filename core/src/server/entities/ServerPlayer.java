package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.CharacterState;



public class ServerPlayer extends ServerCharacter {
	
	public ServerPlayer(Body body) {
		super(body);
	}
	
	public void update(KeysPressed pressed) {
		float forceX = (pressed.left * -FORCE_CONSTANT) + (pressed.right *FORCE_CONSTANT);
		float forceY = (pressed.up * FORCE_CONSTANT) + (pressed.down * -FORCE_CONSTANT);
		// TODO this method may show the wrong stand frame if you're not moving
		this.direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
		this.movement = !(forceX == 0 && forceY == 0);
		this.body.applyForceToCenter(forceX, forceY, true);
	}
	
	@Override
	public CharacterState getState() {
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction);
	}
	
}
