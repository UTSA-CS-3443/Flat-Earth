package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;


public abstract class ServerCharacter implements PosAndDir {

	public ActionTrigger trigger = ActionTrigger.NORMAL;
	
	protected boolean alive;
	public int health = 100;
	
	protected float direction;
	protected boolean movement;
	
	public static final float FORCE_CONSTANT = .07f;
	
	protected static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	
	protected ServerSkill[] skills;
	
	public Body body;
	
	protected ServerGameMap gameMap;
	
	protected float attackFrequency = 10f;
	
	protected float initialX;
	protected float initialY;
	
	
	protected boolean fallingBefore = false;
	protected float fallTimer;
	protected float fallTime = 800f;
	protected float prevForceX;
	protected float prevForceY;
	
	protected boolean deadBefore = false;
	protected float deadTimer;
	protected float deadTime = 800f;
	
	// this is not the one in the client package
	// this is used in the npcs ai whatever, so you only attack different types
	protected CharacterType type;
	
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

	public PositionAndType getPositionAndType() {
		return new PositionAndType(this.body.getPosition(), this.type);
	}
	
	@Override 
	public Vector2 getPosition() {
		return this.body.getPosition();
	}

	@Override
	public float getDirection() { return this.direction; }
	
	public abstract CharacterState getState();

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




































