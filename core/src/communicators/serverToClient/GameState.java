package communicators.serverToClient;

import java.util.ArrayList;

import utilities.Sys;

/**
 * this class is set by the Controller/logic handler
 * looks really similar for now to keyboard state but will eventually hold way more things,
 * like enemy positions
 * @author mauricio
 *
 */


public class GameState {
	
	public ArrayList<CharacterState> states;
	public SkillState[] skillStates;
	
	public GameState(int amt) {
		states = new ArrayList<CharacterState>();
		for (int i = 0; i < amt; i++)
			states.add(new CharacterState());
	}
	
	
	public synchronized CharacterState getState(int id) {
		return this.states.get(id);
	}
	
	public synchronized void setState(int id, CharacterState state) {
		this.states.get(id).set(state);
	}
	
	public synchronized void setStates(CharacterState cs[]) {
		//System.out.printf("cs is : %d, states is", cs.length);
		for (int i = 0; i < this.states.size(); i++)
			this.setState(i, cs[i]);
			//this.states.get(i).set(cs[i]);
	}
	
	// remarkably inefficient, jesus christ 
	public synchronized void setSkillStates(SkillState[] sss) {
		this.skillStates = sss;
	}
	
	// TODO ? might need to figure out ways around using synchronized
	public synchronized CharacterState[] getCharacterStates() {

		CharacterState cs[] = new CharacterState[this.states.size()];
		cs = this.states.toArray(cs);
		return cs;
		
	}

	public synchronized SkillState[] getSkillStates() {
		return this.skillStates;
	}
	
}

















































