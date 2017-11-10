package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.CharacterState;


public abstract class ServerCharacter {

	protected boolean alive;
	
	protected float direction;
	protected boolean movement;
	
	protected static final int FORCE_CONSTANT = 1;
	
	protected static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	protected Body body;
	
	public ServerCharacter (Body body) {
		this.body = body;
		this.body.setLinearDamping(5);
		this.body.setAngularDamping(5);
	}
	
	public Vector2 getVector() {
		return this.body.getPosition();
	}
	
	public abstract CharacterState getState();
	
}