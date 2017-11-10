package server.entities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.CharacterState;
import utilities.Exit;

public class ServerEntityManager {

	ArrayList<ServerCharacter> characters; // TODO this is not optimized
	
	public ServerEntityManager() {
		characters = new ArrayList<ServerCharacter>();
	}
	
	// if the id is even needed
	public void add(ServerCharacter character) {
		characters.add(character);
	}
	
	/**
	 * pressed.length will also be the amount of players, so loop through the first n in
	 * the array list (where n is the amount of players), update them, then update the 
	 * rest based on the coordinates (Vector2's) of those players
	 * @param pressed
	 */
	public void updateAll(KeysPressed pressed[]) {
		int i;
		
		Vector2 v[] = new Vector2[pressed.length];
		
		for (i = 0; i < pressed.length; i++) {
			ServerCharacter c = characters.get(i);
			((ServerPlayer)c).update(pressed[i]);
			v[i] = c.getVector();
		}
		
		for (i = pressed.length; i < characters.size(); i++)
			((ServerNpc)characters.get(i)).update(v);
	}
	
	/**
	 * returns a list of all the character states. used for sending to the client 
	 * @return
	 */
	public CharacterState[] getStates() {
		CharacterState css[] = new CharacterState[this.characters.size()];
		for (int i = 0; i < characters.size(); i++)
			css[i] = this.characters.get(i).getState();
		return css;
	}
}















