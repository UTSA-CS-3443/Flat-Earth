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


/**
 * Main class for client side, holds the game loop. Libgdx
 * runs this
 * 
 * @author ash
 * @author mauricio
 *
 */
public class Game extends ApplicationAdapter {
	/**
	 * batch that gets drawn
	 */
	public static SpriteBatch batch;
	/**
	 * camera that follows player
	 */
	public static OrthographicCamera cam;
	/**
	 * map
	 */
	public ClientGameMap map;
	/**
	 * assets
	 */
	public static AssetManager assets;
	/**
	 * game atlas, holds the sprites
	 */
	public static TextureAtlas atlas;
			
	/**
	 * Game state
	 */
	private GameState gs;
	/**
	 * KeybaordState for player
	 */
	private KeyboardState ks;
	
	/**
	 * id used by server
	 */
	public int clientId; 
	
	/**
	 * settings chosen at start
	 */
	private Settings s;
	
	/**
	 * map details 
	 */
	MapDetails details;
	
	// for debugging
	/**
	 * debug mode
	 */
	public static boolean debug = true;
	/**
	 * debug mode
	 */
	public static boolean debugFromStart = false;
	/**
	 * debug stuff
	 */
	public static Box2DDebugRenderer b2dr;
	/**
	 * debug stuff
	 */
	public static Matrix4 debugMatrix;

	
	/**
	 * @param s setting froms beginning of gmae
	 * @param gs GameState shared by modifiyng thread
	 * @param ks kaybaord state of this player
	 * @param details map stuff
	 */
	public Game(Settings s, GameState gs, KeyboardState ks, MapDetails details) {
		this.ks = ks;
		this.gs = gs;
		this.s = s;
		this.clientId = s.clientId;
		this.details = details;
	}

	// TODO all this needs to be less hardcoded
	// cant tell if this is an old comment 
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#create()
	 */
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

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#render()
	 */
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
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#dispose()
	 */
	@Override
	public void dispose () {
		batch.dispose();
		//this.map.dispose();
	}
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#resize(int, int)
	 */
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
