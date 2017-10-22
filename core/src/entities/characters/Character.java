package entities.characters;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import utilities.GameState;

public abstract class Character
{
	enum Direction { NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST; }
	private Direction[] directionArray = Direction.values();
	private CharacterType type;
	protected int frameIndex;
	protected float frameTime;
	private Direction direction;
	public Body body;
	
	protected Character(CharacterType type, Body body)
	{
		
		this.direction = Direction.SOUTH; // Default
		this.type = type;
		this.frameIndex = 0;
		this.body = body;
		this.body.setLinearDamping(5);
		this.body.setAngularDamping(5);
		
	}
	
	public abstract void update(float delta, GameState gs);
	
	public void updateDirection()
	{
		float degrees = degreeFix((float) Math.toDegrees(this.body.getAngle()));
		direction = this.directionArray[((int)degrees/45)-1];
	}
	
	public Sprite getFrame()
	{		
		//if attacking
		//return getAttackFrame();
		if(this.body.getLinearVelocity().x == 0 && this.body.getLinearVelocity().y == 0)
			return getStandFrame();
		else //walking
			return getWalkFrame();
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
		angle = ((int) angle % 360) + (angle - ((int)angle));
		if(angle > 0.0)
		{
			return angle;
		}
		else
		{
			return angle + 360f;
		}
	}

	

}
