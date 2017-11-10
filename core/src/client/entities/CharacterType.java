package client.entities;

import client.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.utils.Array;

//import zzzztrash.com.flatearth.*;

/**
 * Creates AtlasSprite animation arrays for each type of character.
 * An AtlasSprite is like a Sprite but expects the format of a sprite
 * in an atlas. Who'd have guessed?
 * @author Siavash
 *
 */
public enum CharacterType
{
	KNIGHT(1, 3, 3, "knight"), WIZARD(1, 3, 3, "wizard"), ARCHER(1, 3, 3, "archer");
	
	private Array<AtlasSprite> attackLeft, attackFrontLeft, attackFront, attackFrontRight, attackRight, attackBack, attackBackLeft, attackBackRight,
	walkLeft, walkFrontLeft, walkFront, walkFrontRight, walkRight, walkBack, walkBackLeft, walkBackRight;
	private Sprite standLeft, standFrontLeft, standFront, standFrontRight, standRight, standBack, standBackLeft, standBackRight;
	
	/**
	 * @param standFrame - What index of the animation is the character standing at?
	 * @param attackFrameCount - How many frames is the characters attack?
	 * @param runFrameCount - How many frames is the characters run?
	 * @param name - What is the characters name
	 */
	CharacterType(int standFrame, int attackFrameCount, int runFrameCount, String name)
	{
		// Haven't done these animations yet
//		attackLeft = addAnimation(name, "attack", "left", 1, 3);
//		attackRight = addAnimation(name, "attack", "right", 1, 3);
//		attackFront = addAnimation(name, "attack", "front", 1, 3);
//		attackBack = addAnimation(name, "attack", "back", 1, 3);
//		attackFrontLeft = addAnimation(name, "attack", "front left", 1, 3);
//		attackFrontRight = addAnimation(name, "attack", "front right", 1, 3);
//		attackBackLeft = addAnimation(name, "attack", "back left", 1, 3);
//		attackBackRight = addAnimation(name, "attack", "back right", 1, 3);
		
		walkLeft = addAnimation(name, "walk", "side", standFrame, runFrameCount, true);
		standLeft = walkLeft.get(standFrame);
		
		walkRight = addAnimation(name, "walk", "side", standFrame, runFrameCount, false);
		standRight = walkRight.get(standFrame);
		
		walkFront = addAnimation(name, "walk", "front", standFrame, runFrameCount, false);
		standFront = walkFront.get(standFrame);
		
		walkBack = addAnimation(name, "walk", "back", standFrame, runFrameCount, false);
		standBack = walkBack.get(standFrame);
		
		walkFrontLeft = addAnimation(name, "walk", "front side", standFrame, runFrameCount, true);
		standFrontLeft = walkFrontLeft.get(standFrame);
		
		walkFrontRight = addAnimation(name, "walk", "front side", standFrame, runFrameCount, false);
		standFrontRight = walkFrontRight.get(standFrame);
		
		walkBackLeft = addAnimation(name, "walk", "back side", standFrame, runFrameCount, true);
		standBackLeft = walkBackLeft.get(standFrame);
		
		walkBackRight = addAnimation(name, "walk", "back side", standFrame, runFrameCount, false);
		standBackRight = walkBackRight.get(standFrame);
	}
	
	/**
	 * @param name - What is the characters name
	 * @param type - Walk, Attack, etc..
	 * @param direction - Front, Back, etc...
	 * @param standFrame - What index of the animation is the character standing at?
	 * @param frameCount - How many frames is the characters animation?
	 * @param flip - Should the animation be flipped? (like, right to left animations)
	 * @return - The animation
	 */
	private Array<AtlasSprite> addAnimation(String name, String type, String direction, int standFrame, int frameCount, boolean flip)
	{
		Array<AtlasSprite> animation = new Array<AtlasSprite>();
		int count = 1;
		for(int i = 0; i < frameCount; i ++)
		{
			if(i == standFrame)
				animation.add(new AtlasSprite(Game.atlas.findRegion(name + " stand " + direction)));
			else
			{
				animation.add(new AtlasSprite(Game.atlas.findRegion(name + " " + type + " " + direction + " " + count)));
				count++;
			}
		}
		if(standFrame >= 0)
		{
			animation.add(new AtlasSprite(Game.atlas.findRegion(name + " stand " + direction)));
		}
		for(int i = 0; i < animation.size; i++)
		{
			animation.get(i).flip(flip,  false);
			animation.get(i).setSize(1, 1);
		}
		return animation;
	}
	
	public Array<AtlasSprite> getAttackLeft() {
		return attackLeft;
	}
	public Array<AtlasSprite> getAttackFrontLeft() {
		return attackFrontLeft;
	}
	public Array<AtlasSprite> getAttackFront() {
		return attackFront;
	}
	public Array<AtlasSprite> getAttackFrontRight() {
		return attackFrontRight;
	}
	public Array<AtlasSprite> getAttackRight() {
		return attackRight;
	}
	public Array<AtlasSprite> getAttackBack() {
		return attackBack;
	}
	public Array<AtlasSprite> getAttackBackLeft() {
		return attackBackLeft;
	}
	public Array<AtlasSprite> getAttackBackRight() {
		return attackBackRight;
	}
	public Array<AtlasSprite> getWalkLeft() {
		return walkLeft;
	}
	public Array<AtlasSprite> getWalkFrontLeft() {
		return walkFrontLeft;
	}
	public Array<AtlasSprite> getWalkFront() {
		return walkFront;
	}
	public Array<AtlasSprite> getWalkFrontRight() {
		return walkFrontRight;
	}
	public Array<AtlasSprite> getWalkRight() {
		return walkRight;
	}
	public Array<AtlasSprite> getWalkBack() {
		return walkBack;
	}
	public Array<AtlasSprite> getWalkBackLeft() {
		return walkBackLeft;
	}
	public Array<AtlasSprite> getWalkBackRight() {
		return walkBackRight;
	}
	public Sprite getStandLeft() {
		return standLeft;
	}
	public Sprite getStandFrontLeft() {
		return standFrontLeft;
	}
	public Sprite getStandFront() {
		return standFront;
	}
	public Sprite getStandFrontRight() {
		return standFrontRight;
	}
	public Sprite getStandRight() {
		return standRight;
	}
	public Sprite getStandBack() {
		return standBack;
	}
	public Sprite getStandBackLeft() {
		return standBackLeft;
	}
	public Sprite getStandBackRight() {
		return standBackRight;
	}
}
