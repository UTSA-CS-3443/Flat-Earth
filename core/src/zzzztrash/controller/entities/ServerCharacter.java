package zzzztrash.controller.entities;

import utilities.CharacterState;
import utilities.KeysPressed;

public abstract class ServerCharacter {
	
	public float x;
	public float y;
	
	public static final int FORCE_CONSTANT = 1;
	public static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	
	public int id;
	
	//public State state;
	
	public int health;
	
	public abstract void serverUpdate(KeysPressed pressed);
	
	public abstract CharacterState getCharacterState();
	
}
