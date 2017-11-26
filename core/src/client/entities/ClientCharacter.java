package client.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import client.skills.ClientHealthBars;
import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import utilities.Sys;


public class ClientCharacter implements Comparable<ClientCharacter>
{
	enum Direction { NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST; }
	protected Direction[] directionArray = Direction.values();
	protected CharacterType type;
	protected int frameIndex;
	protected float frameTime;
	protected Direction direction;
	
	protected boolean charMovement;
	protected float degrees;
	
	public float x;
	public float y;
	
	protected int health;
	protected int maxHealth = 100;
	
	protected float xscale = .9f;
	protected float yscale = 1.1f;
	protected int rotation = 0;	
	protected ActionTrigger trigger = ActionTrigger.NORMAL;
	protected int fallCount = 0;
	
	// this is for the attacking phase
	protected float prevFrameIndex = 0;
	
	
	protected ClientCharacter(CharacterType type)
	{
		this.direction = Direction.SOUTH; // Default
		this.type = type;
		this.frameIndex = 0;	
	}
	
	public void update(float delta, CharacterState cs)
	{
		float frameThreshold = .05f; // for animations
		
		this.charMovement = cs.charMovement;
		this.degrees = cs.charDirection;
		this.x = cs.x;
		this.y = cs.y;
		this.health = cs.health;
		// this is where those finite state machines come into play
		// should probably implement that
		
		if (this.trigger == ActionTrigger.NORMAL && cs.trigger == ActionTrigger.ATTACKING) {
			this.frameIndex = 0;
			this.prevFrameIndex = 0;
			this.trigger = ActionTrigger.ATTACKING;
		}
		
		if (this.trigger == ActionTrigger.ATTACKING) {
			if (this.prevFrameIndex != 0 && this.frameIndex == 0) { //reset attacking phase shit
				this.trigger = ActionTrigger.NORMAL;
			} else {
				frameThreshold = .1f; // gotta move the attacking animations a little slower
			}
		}
		
		//Animation
		frameTime += delta;
		if(frameTime > frameThreshold)
		{
			prevFrameIndex = frameIndex;
			frameIndex ++;
			frameTime = 0;
		}
		if(frameIndex > 3)
		{
			frameIndex = 0;
		}
		
				
		updateDirection();
	}
	
	public void updateDirection()
	{
		int directionIndex = ((int)this.degrees/45)-1;
		if(directionIndex < 0)
			directionIndex = 7;
		direction = Direction.values()[directionIndex];
	}
	
	public Sprite getFrame()
	{		
		//if attacking
		//return getAttackFrame();
		Sprite s = null;
		if (this.trigger == ActionTrigger.ATTACKING)
			s = getAttackFrame();
		else if(!this.charMovement)
			s = getStandFrame();
		else
			s = getWalkFrame(); 
		s.setPosition(this.x - s.getWidth() / 2, this.y);
		return s;
	}
	
	public Sprite getHealthBar() {
		Sprite bar = ClientHealthBars.getAppropriateHealthBar(((float)this.health/this.maxHealth) * 100f);
		bar.setPosition(this.x - bar.getWidth() / 2, this.y + 1.15f);
		return bar;
	}
	
	protected Sprite getWalkFrame()
	{
		switch(this.direction)
		{
			case NORTH:
				return type.getWalkBack().get(frameIndex);
			case WEST:
				return type.getWalkLeft().get(frameIndex);
			case SOUTH:
				return type.getWalkFront().get(frameIndex);
			case EAST:
				return type.getWalkRight().get(frameIndex);
			case NORTHWEST:
				return type.getWalkBackLeft().get(frameIndex);
			case NORTHEAST:
				return type.getWalkBackRight().get(frameIndex);
			case SOUTHWEST:
				return type.getWalkFrontLeft().get(frameIndex);
			default: //SOUTHEAST
				return type.getWalkFrontRight().get(frameIndex);
		}
	}
	
	protected Sprite getStandFrame()
	{
		switch(this.direction)
		{
			case NORTH:
				return type.getStandBack();
			case WEST:
				return type.getStandLeft();
			case SOUTH:
				return type.getStandFront();
			case EAST:
				return type.getStandRight();
			case NORTHWEST:
				return type.getStandBackLeft();
			case NORTHEAST:
				return type.getStandBackRight();
			case SOUTHWEST:
				return type.getStandFrontLeft();
			default: //SOUTHEAST
				return type.getStandFrontRight();
		}
	}
	
	protected Sprite getAttackFrame()
	{
		switch(this.direction)
		{
			case NORTH:
				return type.getAttackBack().get(frameIndex);
			case WEST:
				return type.getAttackLeft().get(frameIndex);
			case SOUTH:
				return type.getAttackFront().get(frameIndex);
			case EAST:
				return type.getAttackRight().get(frameIndex);
			case NORTHWEST:
				return type.getAttackBackLeft().get(frameIndex);
			case NORTHEAST:
				return type.getAttackBackRight().get(frameIndex);
			case SOUTHWEST:
				return type.getAttackFrontLeft().get(frameIndex);
			default: //SOUTHEAST
				return type.getAttackFrontRight().get(frameIndex);
		}
	}
	
	private float degreeFix(float angle)
	{
	    angle %= 360f;
	    if (angle <= 0f)
	        return angle + 360f;
	    return angle;
	}

	@Override
	public int compareTo(ClientCharacter other) {
		if (this.y > other.y)
			return -1;
		else if (this.y < other.y)
			return 1;
		return 0;
	}
	
}




















