package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;
import utilities.Sys;


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
	protected float fallTime = 10f;
	protected float prevForceX;
	protected float prevForceY;
	
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
				this.body.setTransform(this.body.getPosition().x + this.prevForceX*30, this.body.getPosition().y + this.prevForceY*30, this.direction);
			}
			this.fallTimer += delta;
			if (this.fallTimer > this.fallTime) {
				this.trigger = ActionTrigger.NORMAL;
				//this.body.getPosition().set(initialX, initialY);
				this.body.setTransform(initialX, initialY, 315);
				this.fallingBefore = false;
			} else 
				return true;
		}
		return false;
	}
	
}




































