package client.entities;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import client.skills.ClientArrow;
import client.skills.ClientFireBall;
import client.skills.ClientHealthBars;
import client.skills.ClientSkill;
import client.skills.ClientSlash;
import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import communicators.serverToClient.SkillState;
import server.skills.SkillType;
import utilities.Sys;

/**
 * manages the entities on screen, updates them
 * 
 * @author mauricio
 *
 */
public class ClientEntityManager {

	/**
	 * list of ccharacters in order sent from server
	 */
	private ArrayList<ClientCharacter> charactersUpdateOrder;
	/**
	 * same list but sorted by y
	 */
	private ArrayList<ClientCharacter> charactersYOrder;
	
	/**
	 * list of skills
	 */
	private ArrayList<ClientSkill> skills;
	
	/**
	 * counter for death flames
	 */
	private float flameCounter = 0f;
	
	/**
	 * 
	 */
	public ClientEntityManager() {
		charactersUpdateOrder = new ArrayList<ClientCharacter>();
		charactersYOrder = new ArrayList<ClientCharacter>();
		skills = new ArrayList<ClientSkill>();
		ClientHealthBars.initialize();
		DeadFlameAnimation.initialize();
	}
	
	/**
	 * adds a characetrs to the list
	 * 
	 * @param character
	 */
	public void add(ClientCharacter character) {
		charactersUpdateOrder.add(character);
		charactersYOrder.add(character);
	}
	
	
	
	/**
	 * @param delta time
	 * @param cs states of all characters from server
	 * @param ss states of skills from sever
	 */
	public void updateAll(float delta, CharacterState cs[], SkillState ss[]) {
		for (int i = 0; i < cs.length; i++)
			charactersUpdateOrder.get(i).update(delta, cs[i]);
		updateSkillsList(ss);
		for (int i = 0; i < ss.length; i++)
			skills.get(i).update(ss[i]);
	}
	
	/**
	 * draws everything
	 * 
	 * @param batch
	 */
	public void drawAll(SpriteBatch batch) {
		Collections.sort(charactersYOrder);
		
		for (ClientCharacter c : charactersYOrder) {
			c.getFrame().draw(batch);
			if (c.trigger == ActionTrigger.DEAD) {
				Sprite s = DeadFlameAnimation.getFlame((int)flameCounter);
				s.setPosition(c.x - c.getFrame().getWidth()/2, c.y);
				s.draw(batch);
				flameCounter += .18f;
				if (flameCounter >= 100f)
					flameCounter = 0f;
			}
			c.getHealthBar().draw(batch);
		}
		
		for (ClientSkill cs : this.skills) {
			if(cs.type == SkillType.ARROW || cs.type == SkillType.FIREBALL)
				cs.getFrame().draw(batch);
		}
			
	}
	
	/**
	 *  kinda ugly but should kind of work
	 * updates the skill list based on whats gotten from server
	 * @param ss
	 */
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























