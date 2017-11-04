package com.flatearth;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import entities.characters.Knight;
import entities.characters.NpcWizard;
import entities.characters.SpawnGenerator;
import entities.characters.Wizard;
import entities.EntityManager;
import utilities.CharacterState;
import utilities.GameState;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class GameMap
{
	private Sprite mapSprite;
	private World world;
	
	private Knight knight;
	//private Knight wiz;
	private NpcWizard wiz;
	//private Wizard wizard;
	//private Archer archer;
	
	private float dt = 0.0133f;
	private float accumulator;
	private GameState gs;
	
	public EntityManager ntit_man;
	
	/**
	 * Makes a map that is 10 times the size of
	 * its original, tiny size
	 */
	public GameMap(String name, GameState gs)
	{
		// world is for the physics. you create and destroy bodies through the world
		this.world = new World(new Vector2(0, 0), false);
		this.mapSprite = new Sprite(Game.atlas.findRegion(name));
		this.mapSprite.setSize(this.mapSprite.getWidth() / 5, this.mapSprite.getHeight() / 5);
		
		knight = SpawnGenerator.spawnPlayer(Knight.class, (this.mapSprite.getX()+ this.mapSprite.getWidth()/2),
                (this.mapSprite.getY() + this.mapSprite.getHeight()/2), this.world);
		wiz = SpawnGenerator.spawnNpc(NpcWizard.class, (this.mapSprite.getX()+ this.mapSprite.getWidth()/2),
                (this.mapSprite.getY() + this.mapSprite.getHeight()/2), this.world);
		
		ntit_man = new EntityManager(knight);
		ntit_man.add(wiz);
		this.gs = gs;
	}
	
	/**
	 * Makes the game increment logic due to elapsed time.
	 * Effects things like physics and animations.
	 * @param delta- Elapsed time since the last frame
	 */
	public void update(float delta)
	{
		
		accumulator += delta;
		while (accumulator >= dt)
		{
			//get CharaceterState[] from game state
			CharacterState cs[] = this.gs.getCharacterStates();
			world.step(dt, 6, 2);
			ntit_man.updateAll(dt, cs);
            accumulator -= dt;
		}
        Game.cam.position.x = this.knight.getBody().getPosition().x + (this.knight.getFrame().getWidth()/2);
        Game.cam.position.y = this.knight.getBody().getPosition().y + (this.knight.getFrame().getHeight()/2);
        Game.cam.update();
	}
	
	/**
	 * Take in FlatEarths static batch to render the map sprite,
	 * and all of the characters and objects on the map.
	 */
	public void draw(SpriteBatch batch)
	{
		this.mapSprite.draw(batch);
		this.ntit_man.drawAll(batch);
	}
	
	public void dispose()
	{
		this.world.dispose();
	}
	
	public World getWorld() { 
		return this.world; 
	}

}
