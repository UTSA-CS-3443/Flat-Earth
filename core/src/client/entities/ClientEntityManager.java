package client.entities;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import client.skills.ClientArrow;
import client.skills.ClientFireBall;
import client.skills.ClientHealthBars;
import client.skills.ClientSkill;
import client.skills.ClientSlash;
import communicators.serverToClient.CharacterState;
import communicators.serverToClient.SkillState;
import server.skills.SkillType;
import utilities.Sys;

public class ClientEntityManager {

	private ArrayList<ClientCharacter> charactersUpdateOrder;
	private ArrayList<ClientCharacter> charactersYOrder;
	
	private ArrayList<ClientSkill> skills;
	
	
	public ClientEntityManager() {
		charactersUpdateOrder = new ArrayList<ClientCharacter>();
		charactersYOrder = new ArrayList<ClientCharacter>();
		skills = new ArrayList<ClientSkill>();
		ClientHealthBars.initialize();
	}
	
	public void add (ClientCharacter character) {
		charactersUpdateOrder.add(character);
		charactersYOrder.add(character);
	}
	
	
	
	public void updateAll(float delta, CharacterState cs[], SkillState ss[]) {
		for (int i = 0; i < cs.length; i++)
			charactersUpdateOrder.get(i).update(delta, cs[i]);
		updateSkillsList(ss);
		for (int i = 0; i < ss.length; i++)
			skills.get(i).update(ss[i]);
	}
	
	public void drawAll(SpriteBatch batch) {
		Collections.sort(charactersYOrder);
		
		for (ClientCharacter c : charactersYOrder) {
			c.getFrame().draw(batch);
			c.getHealthBar().draw(batch);
		}
		
		for (ClientSkill cs : this.skills) {
			if(cs.type == SkillType.ARROW || cs.type == SkillType.FIREBALL)
				cs.getFrame().draw(batch);
		}
			
	}
	
	// kinda ugly but should kind of work
	public void updateSkillsList(SkillState ss[]) {
		
		if (ss.length == 0) { // empty the list
			this.skills = new ArrayList<ClientSkill>();
			return;
		}
		
		for (int i = 0; i < ss.length && i < this.skills.size(); i++) { // remove all the in between wrong stuff
			while(i < this.skills.size() && ss[i].type != this.skills.get(i).type) {
				this.skills.remove(i);
			}
		}
		
		// if my skills is still longer than my length, then remove the extra
		if (this.skills.size() > ss.length) {
			
			while (ss.length < skills.size()) {
				skills.remove(ss.length);
			}
			
		} else if (ss.length > this.skills.size()) { // else if ss is longer than skills, add the new stuff
			
			for (int i = this.skills.size(); i < ss.length; i++) {
				switch(ss[i].type) {
				case ARROW:
					this.skills.add(new ClientArrow(ss[i]));
					break;
				case FIREBALL:
					this.skills.add(new ClientFireBall(ss[i]));
					break;
				case SLASH:
					this.skills.add(new ClientSlash(ss[i]));
					break;
				default:
					Sys.exit("ClientEntityManager.updateSkillsList() : Not handling this type of skill yet");
				}
			}
		
		}
		
	
	}
	
}























