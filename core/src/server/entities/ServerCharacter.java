package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.CharacterState;
import utilities.ActionState;


public abstract class ServerCharacter implements PosAndDir {

	public ActionState state = ActionState.NORMAL;
	
	protected boolean alive;
	protected int health = 100;
	
	protected float direction;
	protected boolean movement;
	
	protected static final float FORCE_CONSTANT = .07f;
	
	protected static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	public Body body;
	
	public ServerCharacter (Body body) {
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