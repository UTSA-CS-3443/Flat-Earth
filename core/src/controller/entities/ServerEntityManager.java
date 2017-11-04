package controller.entities;

import java.util.ArrayList;
import utilities.KeysPressed;
import utilities.CharacterState;

public class ServerEntityManager {
	
	public ArrayList<ServerPlayer> players;
	public ArrayList<ServerNpc> npcs;
	
	public ServerEntityManager() {
		players = new ArrayList<ServerPlayer>();
		npcs = new ArrayList<ServerNpc>();
	}
	
	public void addNpc(ServerNpc npc) {
		npcs.add(npc);
	}
	
	public void addPlayer(ServerPlayer player) {
		players.add(player);
	}
	
	/*public ServerPlayer getPlayer(int id) {
		return this.players.get(id);
	}*/
	
	// TODO this only takes one pressed, probably needs to take an array 
	// for the server client stuff
	public void serverUpdateAll(KeysPressed pressed) {
		
		// right now im making the assumption that id id matches up with the index
		// in the array. Probably not a safe assumption. id's are set to the character
		// too, so maybe should be searching based on that. either that or actually
		// set this architecture to always be in order of id. read more in logic.java
		// above the nextId() method
		players.get(pressed.id).serverUpdate(pressed);
		for (ServerNpc npc : this.npcs)
			npc.serverUpdate(pressed);
//		for (ServerPlayer player : players)
//			player.serverUpdate(pressed);
//		for (ServerNpc npc : npcs)
//			npc.serverUpdate(pressed); // wont use 'pressed', just bad design
	}
	
	// TODO man this baaaadly optimized but it works for now
	public CharacterState[] getCharacterStates() {
		ArrayList<CharacterState> states = new ArrayList<CharacterState>();
		for (ServerPlayer p : players)
			states.add(p.getCharacterState());
		for (ServerNpc n : npcs)
			states.add(n.getCharacterState());
		CharacterState cs[] = new CharacterState[states.size()];
		cs = states.toArray(cs);
		return cs;
	}
	
	

}










