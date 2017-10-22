package entities;

import java.util.ArrayList;

import entities.characters.Character;
import utilities.GameState;

public class EntityManager {
	
	ArrayList<Character> entities;
	
	public void killNpcs() {
		
	}
	
	public void kill(Character c) {
		// TODO maybe some logic
		entities.remove(c);
	}
	
	public void updateAll(float delta, GameState gs) {
		for (Character c : this.entities)
			c.update(delta, gs);
	}
	
	public void add(Character c) {
		entities.add(c);
	}
	
}












