package client.entities;


import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.serverToClient.CharacterState;
import utilities.ActionState;
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
	
	protected float scale = 1f;
	protected int rotation = 0;	
	protected ActionState state = ActionState.NORMAL;
	
	protected ClientCharacter(CharacterType type)
	{
		this.direction = Direction.SOUTH; // Default
		this.type = type;
		this.frameIndex = 0;		
	}
	
	public void update(float delta, CharacterState cs)
	{
		//Animation
		frameTime += delta;
		if(frameTime > .05f)
		{
			frameIndex ++;
			frameTime = 0;
		}
		if(frameIndex > 3)
		{
			frameIndex = 0;
		}
		
		this.charMovement = cs.charMovement;
		this.degrees = cs.charDirection;
		this.x = cs.x;
		this.y = cs.y;
		this.health = cs.health;
		this.state = cs.state;
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
		if(!this.charMovement)
			s = getStandFrame();
		else
			s = getWalkFrame();
		
		if (state == ActionState.FALLING) {
			s.scale(this.scale * 0.5f);
			s.rotate(this.rotation + 15);
		} else  {
			s.setPosition(this.x - s.getWidth() / 2, this.y);
		}
		return s;
	}
	
	private Sprite getWalkFrame()
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
	
	private Sprite getStandFrame()
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
	
	private Sprite getAttackFrame()
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




















