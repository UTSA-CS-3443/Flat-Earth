package com.flatearth;

import java.util.concurrent.ExecutorService;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import utilities.*;

public class Game extends ApplicationAdapter {
	public static SpriteBatch batch;
	public GameMap map;
	public static AssetManager assets;
	public static TextureAtlas atlas;
	
	public static boolean debug = false;
	public static Box2DDebugRenderer b2dr;
	public static Matrix4 debugMatrix;
			
	private GameState gs;
	private KeyboardState ks;
		
	public Game(Settings s, GameState gs, KeyboardState ks) {
		this.ks = ks;
		this.gs = gs;
	}
	
	@Override
	public void create () {
		//statics
		batch = new SpriteBatch();
		assets = new AssetManager();
		loadAssets();
		
		this.map = new GameMap("map", this.gs);

		// currently not doing anything
		Gdx.input.setInputProcessor(new GameInput(this.ks));
		
		//box2d debug
		// is this what paints the hit boxes?
		debugMatrix = batch.getProjectionMatrix().cpy();
		b2dr = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		//if (pressedShit) {
		//	System.out.println("some thing is pressed");
		//}
		//Clear the screen to a black background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.map.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		this.map.draw(batch); //render everything that the map has
		batch.end();
		
		if(Game.debug)
		{
			Game.b2dr.render(this.map.getWorld(), Game.debugMatrix);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.map.dispose();
	}
	
	/**
	 * Loads all assets, such as
	 * images and sounds now, instead
	 * of using internal paths to create
	 * them later, when you need them.
	 */
	private void loadAssets()
	{
		assets.load("atlas.atlas", TextureAtlas.class);
		assets.finishLoading();
		atlas = assets.get("atlas.atlas");
	}
}
