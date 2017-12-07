package server.entities;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.utils.Array;

import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.CharacterState;
import communicators.serverToClient.SkillState;
import server.ServerGameMap;
import server.Updateable;
import server.skills.ServerSkill;

/**
 * @author mauricio
 *
 */
public class ServerEntityManager {

	/**
	 * list of characters
	 */
	public ArrayList<ServerCharacter> characters; // TODO this is not optimized
	/**
	 * bodies to destroy next round, modded by skills themselces
	 */
	public Array<Body> bodiesToDestroy;
	/**
	 * polygon holes
	 */
	ArrayList<Body> holes;
	/**
	 * map
	 */
	private ServerGameMap gameMap;

	/**
	 * updateables
	 */
	public Array<Updateable> updateables;
	/**
	 * skills
	 */
	public Array<ServerSkill> activeSkills;
	
	
	/**
	 * @param gameMap map
	 */
	public ServerEntityManager(ServerGameMap gameMap) {
		this.gameMap = gameMap;
		this.bodiesToDestroy = new Array<Body>();
		this.updateables = new Array<Updateable>();
		this.activeSkills = new Array<ServerSkill>();
		characters = new ArrayList<ServerCharacter>();
		holes = new ArrayList<Body>();
	}
	
	// if the id is even needed
	/**
	 * add a character
	 * @param character
	 */
	public void add(ServerCharacter character) {
		characters.add(character);
	}
	
	/**
	 * pressed.length will also be the amount of players, so loop through the first n in
	 * the array list (where n is the amount of players), update them, then update the 
	 * rest based on the coordinates (Vector2's) of those players
	 * @param pressed keys pressed by each player
	 * @param delta time
	 */
	public void updateAll(KeysPressed pressed[], float delta) {
		
		int i;
		PositionAndType pts[] = new PositionAndType[characters.size()];
		
		// update the players and get their positions
		for (i = 0; i < pressed.length; i++) {
			ServerCharacter c = characters.get(i);
			((ServerPlayer)c).update(pressed[i], delta);
			pts[i] = c.getPositionAndType();
		}
		
		// get the positions of the npcs
		for(i = pressed.length; i < characters.size(); i++)
			pts[i] = characters.get(i).getPositionAndType();
			
		// update the npcs
		for (i = pressed.length; i < characters.size(); i++)
			((ServerNpc)characters.get(i)).update(pts, delta, i);
		
		// update the skills
		for(i = 0; i < updateables.size; i++)
			updateables.get(i).update(delta);
		
		// get rid of dead skills
		for(i = 0; i < bodiesToDestroy.size; i++)
			this.gameMap.getWorld().destroyBody(bodiesToDestroy.get(i));
		bodiesToDestroy.clear();
	}
	
	/**
	 * returns a list of all the character states. used for sending to the client 
	 * there might be ways to optimize this, cause this is a lot of iterations TODO
	 * @return
	 */
	/**
	 * @return
	 */
	public CharacterState[] getCharacterStates() {
		CharacterState css[] = new CharacterState[this.characters.size()];
		for (int i = 0; i < characters.size(); i++) {
			css[i] = this.characters.get(i).getState();
		}
		return css;
	}
	
	/**
	 * @return
	 */
	public SkillState[] getSkillStates() {
		SkillState[] ss = new SkillState[this.activeSkills.size];
		for (int i = 0; i < activeSkills.size; i++)
			ss[i] = this.activeSkills.get(i).getState();
		return ss;
	}
	
	/**
	 * add a hole
	 * @param body hole to add
	 */
	public void addHole(Body body) {
		holes.add(body);
	}
	
}
















