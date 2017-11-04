package entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.characters.Npc;
import entities.characters.Player;
import entities.characters.Character;
import utilities.CharacterState;
import utilities.GameState;

public class EntityManager {

	public ArrayList<Character> characters;
	public Player player;
	
	public EntityManager(Player player) {
		characters = new ArrayList<Character>();
		this.player = player;
		characters.add(player);
	}
	
	public void kill(Character character) {
		//this.npcs.remove(npc);
	}
	
	public void add(Character character) {
		this.characters.add(character);
	}
	
	public void updateAll(float delta, CharacterState cs[]) {
		//System.out.printf("cs size is %d characters is %d\n", cs.length, characters.size());
		for (int i = 0; i < characters.size(); i++) {
			// this should be in the same order as the CharacterState array, needs to be 
			// coordinated right by the server and this side
			this.characters.get(i).update(delta, cs[i]);
		}
	}
	
	public void drawAll(SpriteBatch batch) {
		
		for (Character c : characters) 
			c.getFrame().draw(batch);
		
	}
	
}







