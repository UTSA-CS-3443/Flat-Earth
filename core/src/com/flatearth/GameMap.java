package com.flatearth;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import entities.SpawnGenerator;
import entities.characters.Knight;
import entities.characters.WizardNpc;
import utilities.GameState;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameMap
{
	private Sprite mapSprite;
	private World world;
	
	private Knight knight;
	private WizardNpc wizardNpc;
	//private Wizard wizard;
	//private Archer archer;
	
	private float dt = 0.0133f;
	private float accumulator;
	private GameState gs;
	/**
	 * Makes a map that is 10 times the size of
	 * its original, tiny size
	 */
	public GameMap(String name, GameState gs)
	{
		// world is for the physics. you create and destroy bodies through the world
		this.world = new World(new Vector2(0, 0), false);
		this.mapSprite = new Sprite(Game.atlas.findRegion(name));
		this.mapSprite.setScale(10);
		
		knight = SpawnGenerator.spawnPlayer(Knight.class, 100, 150, this.world);
		wizardNpc = SpawnGenerator.spawnPlayer(WizardNpc.class, 250, 150, world);
		//wizard = SpawnGenerator.spawnPlayer(Wizard.class, 250, 150, this.world);
		//archer = SpawnGenerator.spawnPlayer(Archer.class, 175, 250, this.world);
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
			this.knight.update(dt, this.gs);
			//this.wizard.update(dt);
			//this.archer.update(dt);
			// ash doesn't know what this is necessarily, ctrl-click for declaration
			world.step(dt, 6, 2);
			accumulator -= dt;
		}
	}
	
	/**
	 * Take in FlatEarths static batch to render the map sprite,
	 * and all of the characters and objects on the map.
	 */
	public void draw(SpriteBatch batch)
	{
		this.mapSprite.draw(batch);
		this.knight.getFrame().draw(batch);
		//this.wizard.getFrame().draw(Game.batch);
		//this.archer.getFrame().draw(Game.batch);
	}
	
	public void dispose()
	{
		this.world.dispose();
	}
	
	public World getWorld() { 
		return this.world; 
	}

}
