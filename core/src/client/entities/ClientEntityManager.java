package client.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import communicators.serverToClient.CharacterState;
import utilities.Exit;
//import zzzztrash.entities.characters.Character;

public class ClientEntityManager {

	private ArrayList<ClientCharacter> characters;
	
	public ClientEntityManager() {
		characters = new ArrayList<ClientCharacter>();
	}
	
	public void add (ClientCharacter character) {
		Exit.exit("ClientEntityManager: nothing is happening here");
	}
	
	public void updateAll(float delta, CharacterState cs[]) {
		for (int i = 0; i < cs.length; i++)
			characters.get(i).update(delta, cs[i]);
	}
	
	public void drawAll(SpriteBatch batch) {
		for (ClientCharacter c : characters) 
			c.getFrame().draw(batch);
	}
	
}
