package server.entities;

import com.badlogic.gdx.physics.box2d.Body;

import communicators.ActionTrigger;
import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;



/**
 * For the clients, not the npc. updated by the
 * keys pressed received
 * 
 * @author mauricio
 *
 */
public class ServerPlayer extends ServerCharacter {
	
	/**
	 * last time attacked
	 */
	private float lastAttack = 0;

	
	/**
	 * @param gameMap map its on
	 * @param body physics
	 * @param type type of char
	 */
	public ServerPlayer(ServerGameMap gameMap, Body body, CharacterType type) {
		super(gameMap, body, type);
		this.direction = 270; // direction to start off should be looking down
	}
	
	/**
	 * @param pressed keys that wered pressed
	 * @param delta time
	 */
	public void update(KeysPressed pressed, float delta) {
		
		if(dead(delta))
			return;
		
		if (falling(delta))
			return;
		
		float forceX = (pressed.left * -FORCE_CONSTANT) + (pressed.right *FORCE_CONSTANT);
		float forceY = (pressed.up * FORCE_CONSTANT) + (pressed.down * -FORCE_CONSTANT);
		this.movement = !(forceX == 0 && forceY == 0);
		// only change the direction if some sort of movement is made
		// this fixes some bug where if no movemnt is made, the stand frame will default to north-east
		// direction is used by client for deciding what animation frame to use
		if (this.movement) 
			this.direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
		
		// so you can't just spam attacks
		this.lastAttack += delta;
		
		if (this.lastAttack > this.attackFrequency) {
			this.trigger = ActionTrigger.NORMAL;
		}
		
		if (pressed.attack1 && this.trigger == ActionTrigger.NORMAL) {
			this.trigger = ActionTrigger.ATTACKING;
			ServerSkill skill = this.skills[0].copy();
			skill.setPerformer(this);
			skill.perform(this);
			this.lastAttack = 0;
		}

		this.prevForceX = forceX;
		this.prevForceY = forceY;
		this.body.applyForceToCenter(forceX, forceY, true);
	}
	
	/* (non-Javadoc)
	 * @see server.entities.ServerCharacter#getState()
	 */
	@Override
	public CharacterState getState() {
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction, this.health, this.trigger);
	}
	
}
