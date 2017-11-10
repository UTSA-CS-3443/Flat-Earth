package zzzztrash.controller.entities;

import utilities.CharacterState;
import utilities.KeysPressed;

public class ServerNpc extends ServerCharacter {

	public float forceX;
	public float forceY;
	public float direction;
	public boolean movement;
	
	public ServerNpc(int id) {
		this.id = id;
	}
	
	@Override
	public void serverUpdate(KeysPressed pressed) {
		//TODO make sure to change character state
	}
	
	@Override
	public CharacterState getCharacterState() {
		//TODO this is hardcoded for just working
		return new CharacterState(false, 0, 0, 0);
	}
	
	
}
