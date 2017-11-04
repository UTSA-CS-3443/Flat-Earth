package controller.entities;

import utilities.KeysPressed;
import utilities.CharacterState;

public class ServerPlayer extends ServerCharacter {

	public float forceX;
	public float forceY;
	public float direction;
	public boolean movement;
	
	public ServerPlayer(int id) {
		this.id = id; // in the parent class
	}
	
	@Override
	public void serverUpdate(KeysPressed pressed) {
		forceX = (pressed.left * -FORCE_CONSTANT) + (pressed.right *FORCE_CONSTANT);
		forceY = (pressed.up * FORCE_CONSTANT) + (pressed.down * -FORCE_CONSTANT);
		direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
		movement = !(forceX == 0 && forceY == 0);
	}
	
	@Override
	public CharacterState getCharacterState() {
		return new CharacterState(movement, forceX, forceY, direction);
	}
	
}
