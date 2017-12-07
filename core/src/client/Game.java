package client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

import communicators.clientToServer.KeyboardState;
import communicators.serverToClient.GameState;
import utilities.ParseMap.MapDetails;
import utilities.Settings;


public class Game extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static OrthographicCamera cam;
	public ClientGameMap map;
	public static AssetManager assets;
	public static TextureAtlas atlas;
			
	private GameState gs;
	private KeyboardState ks;
	
	public int clientId; 
	
	private Settings s;
	
	MapDetails details;
	
	// for debugging
	public static boolean debug = true;
	public static boolean debugFromStart = false;
	public static Box2DDebugRenderer b2dr;
	public static Matrix4 debugMatrix;

	
	public Game(Settings s, GameState gs, KeyboardState ks, MapDetails details) {
		this.ks = ks;
		this.gs = gs;
		this.s = s;
		this.clientId = s.clientId;
		this.details = details;
	}

	// TODO all this needs to be less hardcoded
	// cant tell if this is an old comment 
	@Override
	public void create () {
		// TODO have we even given it anything for it too pull w and h?
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        // TODO set this to the players x y stuff, from the client id and the spawn beacons in 
        // game map
        cam = new OrthographicCamera(30, 30 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

		//statics
		batch = new SpriteBatch();
		assets = new AssetManager();
		loadAssets();
		this.map = new ClientGameMap(this.details, this.s);
		
		
		Gdx.input.setInputProcessor(new GameInput(this.ks));
		
		
		// for debugging
		if (debug) {
			debugMatrix = batch.getProjectionMatrix().cpy();
			b2dr = new Box2DDebugRenderer();
		}
		
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.map.update(Gdx.graphics.getDeltaTime(), this.gs);
		
		batch.begin();
		this.map.draw(batch); 
        batch.end();
        
        cam.position.x = map.getPlayer().x + (map.getPlayer().getFrame().getWidth()/2);
        cam.position.y = map.getPlayer().y + (map.getPlayer().getFrame().getHeight()/2);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        
        

        if(Game.debug && Game.debugFromStart) {
            debugMatrix = batch.getProjectionMatrix().cpy();
            Game.b2dr.render(this.map.world, Game.debugMatrix);
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//this.map.dispose();
	}
	@Override
	public void resize(int width, int height) {
	    cam.viewportWidth = width/32f;
		cam.viewportHeight = cam.viewportWidth * height/width;
		cam.update();
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
