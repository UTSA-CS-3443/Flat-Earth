package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;


/**
 * Character super class. parent for ServerPlayer and ServerNpc
 * 
 * @author mauricio
 *
 */
public abstract class ServerCharacter implements PosAndDir {

	/**
	 * trigger states
	 */
	public ActionTrigger trigger = ActionTrigger.NORMAL;
	
	/**
	 * alive
	 */
	protected boolean alive;
	/**
	 * health
	 */
	public int health = 100;
	
	/**
	 * direction
	 */
	protected float direction;
	/**
	 * did i move
	 */
	protected boolean movement;
	
	/**
	 * force for physics
	 */
	public static final float FORCE_CONSTANT = .07f;
	
	/**
	 * directions array, weirdly done
	 */
	protected static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	
	/**
	 * skills that can be done
	 */
	protected ServerSkill[] skills;
	
	/**
	 * physics
	 */
	public Body body;
	
	/**
	 * map
	 */
	protected ServerGameMap gameMap;
	
	/**
	 * how often to attack
	 */
	protected float attackFrequency = 10f;
	
	/**
	 * 
	 */
	protected float initialX;
	/**
	 * 
	 */
	protected float initialY;
	
	
	/**
	 * for falling
	 */
	protected boolean fallingBefore = false;
	/**
	 * fall time
	 */
	protected float fallTimer;
	/**
	 * fall time max
	 */
	protected float fallTime = 800f;
	/**
	 * for fall
	 */
	protected float prevForceX;
	/**
	 * for fall
	 */
	protected float prevForceY;
	
	/**
	 * for death
	 */
	protected boolean deadBefore = false;
	/**
	 * for death
	 */
	protected float deadTimer;
	/**
	 * death max
	 */
	protected float deadTime = 800f;
	
	// this is not the one in the client package
	// this is used in the npcs ai whatever, so you only attack different types
	/**
	 * type of char 
	 */
	protected CharacterType type;
	
	/**
	 * @param gameMap map its on
	 * @param body physics
	 * @param type type of character
	 */
	public ServerCharacter (ServerGameMap gameMap, Body body, CharacterType type) {
		this.gameMap = gameMap;
		this.skills = new ServerSkill[3];
		this.body = body;
		this.body.setLinearDamping(5);
		this.body.setAngularDamping(5);
		this.initialX = body.getPosition().x;
		this.initialY = body.getPosition().y;
		this.type = type;
	}

	/**
	 * @return
	 */
	public PositionAndType getPositionAndType() {
		return new PositionAndType(this.body.getPosition(), this.type);
	}
	
	/* (non-Javadoc)
	 * @see server.entities.PosAndDir#getPosition()
	 */
	@Override 
	public Vector2 getPosition() {
		return this.body.getPosition();
	}

	/* (non-Javadoc)
	 * @see server.entities.PosAndDir#getDirection()
	 */
	@Override
	public float getDirection() { return this.direction; }
	
	/**
	 * @return statet of character
	 */
	public abstract CharacterState getState();

	/**
	 * start and stop falling
	 * 
	 * @param delta time
	 * @return
	 */
	public boolean falling(float delta) {
		if (this.trigger == ActionTrigger.FALLING) {
			if(!this.fallingBefore) {
				this.fallingBefore = true;
				this.fallTimer = 0;
				// hardcoded for now, this is what makes that weird jump, since the body's of the holes are touching
				this.body.setTransform(this.body.getPosition().x + this.prevForceX*10, this.body.getPosition().y + this.prevForceY*10, this.direction);
			}
			this.fallTimer += delta;
			if (this.fallTimer > this.fallTime) {
				this.trigger = ActionTrigger.NORMAL;
				this.body.setTransform(initialX, initialY, 315);
				this.fallingBefore = false;
			} else 
				return true;
		}
		return false;
	}
	
	// copied and pasted the falling code
	/**
	 * @param delta time
	 * @return
	 */
	public boolean dead(float delta) {
		
		if (this.health <= 0)
			this.trigger = ActionTrigger.DEAD;
		
		if (this.trigger == ActionTrigger.DEAD) {
			if(!this.deadBefore) {
				this.deadBefore = true;
				this.deadTimer = 0;
			}
			this.deadTimer += delta;
			if (this.deadTimer > this.deadTime) {
				this.trigger = ActionTrigger.NORMAL;
				this.body.setTransform(initialX, initialY, 315);
				this.deadBefore = false;
				this.health = 100;
			} else 
				return true;
		}
		return false;
	}
	
}




































