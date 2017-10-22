package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

import utilities.GameState;

public class Npc extends Character {

	public Npc(CharacterType type, Body body) {
		super(type, body);
	}

	@Override 
	public void update(float delta, GameState gs) {
		
	}
	
}
