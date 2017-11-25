package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;
import utilities.ActionTrigger;


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
	
	protected float attackFrequency = .5f;
	
	public ServerCharacter (ServerGameMap gameMap, Body body) {
		this.gameMap = gameMap;
		this.skills = new ServerSkill[3];
		this.body = body;
		this.body.setLinearDamping(5);
		this.body.setAngularDamping(5);
	}

	@Override
	public Vector2 getPosition() {
		return this.body.getPosition();
	}

	@Override
	public float getDirection() { return this.direction; }
	
	public abstract CharacterState getState();
	
}