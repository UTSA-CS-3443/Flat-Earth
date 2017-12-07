package client.entities;


import com.badlogic.gdx.graphics.g2d.Sprite;

import client.skills.ClientHealthBars;
import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;


/**
 * Character parent class, used by all types of characters
 * 
 * @author mauricio
 *
 */
public class ClientCharacter implements Comparable<ClientCharacter>
{
	/**
	 * enum for direction
	 * 
	 * @author mauricio
	 *
	 */
	enum Direction { NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST; }
	/**
	 * array of the directions
	 */
	protected Direction[] directionArray = Direction.values();
	/**
	 * all characters have a type
	 */
	protected CharacterType type;
	/**
	 * for the animations
	 */
	protected int frameIndex;
	/**
	 * for the animations
	 */
	protected float frameTime;
	/**
	 * direction of player
	 */
	protected Direction direction;
	
	/**
	 * did the character move this round?
	 */
	protected boolean charMovement;
	/**
	 * degrees received by server
	 */
	protected float degrees;
	
	/**
	 * x position
	 */
	public float x;
	/**
	 * y osition
	 */
	public float y;
	
	/**
	 * health
	 */
	protected int health;
	/**
	 * max helath
	 */
	protected int maxHealth = 100;
	
	/**
	 * scale for falling
	 */
	protected float xscale = .9f;
	/**
	 * scale for falling
	 */
	protected float yscale = 1.1f;
	/**
	 * roation index for falling
	 */
	protected int rotation = 0;	
	/**
	 * triggers different animation states
	 */
	protected ActionTrigger trigger = ActionTrigger.NORMAL;
	/**
	 * how long have i falled?
	 */
	protected int fallCount = 0;
	
	// this is for the attacking phase
	/**
	 * keep track for attacking phase
	 */
	protected float prevFrameIndex = 0;
	
	// this is for the falling animations
	/**
	 * for falling
	 */
	protected float directionDelta = 0f;
	/**
	 * for falling/spinning
	 */
	protected float spinningDegrees = 0f;
	/**
	 * scale for falling
	 */
	protected float fallingScale = 0f;
	
	/**
	 * original sprite width, to fix falling
	 */
	protected float ogSpriteWidth;
	/**
	 * to fix falling
	 */
	protected float ogSpriteHeight;
	
	
	/**
	 * constructs a character
	 * @param type of character this is
	 */
	protected ClientCharacter(CharacterType type)
	{
		this.direction = Direction.SOUTH; // Default
		this.type = type;
		this.frameIndex = 0;	
		this.ogSpriteWidth = getStandFrame().getWidth();
		this.ogSpriteHeight = getStandFrame().getHeight();
	}
	
	// gotta clean this up. finite state machine. really hard coded and messy
	/**
	 * updates the characters position/frame/states based on whatever was sent by the server
	 * 
	 * @param delta time
	 * @param cs state sent by server
	 */
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
		
		/*********************falling phase**********************************************/
		if (this.trigger == ActionTrigger.FALLING && cs.trigger != ActionTrigger.FALLING) { // stop falling
			this.trigger = cs.trigger;
		}
		
		if (this.trigger == ActionTrigger.FALLING) { // continue falling
			directionDelta += 10f;
			this.spinningDegrees += directionDelta;
			if (this.spinningDegrees >= 345) {
				this.spinningDegrees = 0;
				this.directionDelta = 0f;
			}
			this.degrees = this.spinningDegrees;
			this.fallingScale = (fallingScale >= 1) ? .99f : fallingScale + .01f;
		}
		
		if (this.trigger != ActionTrigger.FALLING && cs.trigger == ActionTrigger.FALLING) { // start falling
			this.trigger = ActionTrigger.FALLING;
			directionDelta = 0f;
			this.spinningDegrees = 0f;
			this.fallingScale = 0f;
		}
		
		
		/*********************attacking phase *******************************************************/
		if (this.trigger == ActionTrigger.NORMAL && cs.trigger == ActionTrigger.ATTACKING) { // start attacking
			this.frameIndex = 0;
			this.prevFrameIndex = 0;
			this.trigger = ActionTrigger.ATTACKING;
		}
		
		if (this.trigger == ActionTrigger.ATTACKING) {
			if (this.prevFrameIndex != 0 && this.frameIndex == 0) { //reset attacking phase
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
		
		this.trigger = cs.trigger;
		
		updateDirection();
	}
	
	/**
	 * updates direction
	 */
	public void updateDirection()
	{
		int directionIndex = ((int)this.degrees/45)-1;
		if(directionIndex < 0)
			directionIndex = 7;
		direction = Direction.values()[directionIndex];
	}
	
	/**
	 * @return 
	 */
	public Sprite getFrame()
	{		
		//if attacking
		//return getAttackFrame();
		Sprite s = null;
		float scale = 0;
		if (this.trigger == ActionTrigger.FALLING) {
			s = getStandFrame();
			scale -= fallingScale;
		} else if (this.trigger == ActionTrigger.ATTACKING)
			s = getAttackFrame();
		else if(!this.charMovement)
			s = getStandFrame();
		else
			s = getWalkFrame(); 
		s.setPosition(this.x - s.getWidth() / 2, this.y);
		// hard coded the 1 cause time
		s.setSize(1 + scale, 1 + scale);
		return s;
	}
	
	/**
	 * @return
	 */
	public Sprite getHealthBar() {
		Sprite bar = ClientHealthBars.getAppropriateHealthBar(((float)this.health/this.maxHealth) * 100f);
		bar.setPosition(this.x - bar.getWidth() / 2, this.y + 1.15f);
		return bar;
	}
	
	/**
	 * @return
	 */
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
	
	/**
	 * @return
	 */
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
	
	/**
	 * @return
	 */
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
	
//	private float degreeFix(float angle)
//	{
//	    angle %= 360f;
//	    if (angle <= 0f)
//	        return angle + 360f;
//	    return angle;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ClientCharacter other) {
		if (this.y > other.y)
			return -1;
		else if (this.y < other.y)
			return 1;
		return 0;
	}
	
}




















