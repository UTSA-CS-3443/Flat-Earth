package server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import communicators.clientToServer.KeysPressed;
import server.entities.ServerEntityManager;
import utilities.Exit;
import utilities.ParseMap.MapDetails;

public class ServerGameMap {

	public float length;
	public float width;
	
	private int enemyCount;
	private int playerCount;
	
	private ServerEntityManager entityManager;
	
	private float accumulator;
	private float dt = 0.0133f;
	
	private MapDetails details;
	
	private World world;
	
	
	public ServerGameMap(MapDetails details, int playerCount) {
		this.world = new World(new Vector2(0, 0), false);
		this.playerCount = playerCount;
		this.details = details;
		entityManager = new ServerEntityManager();
	}
	
	public void initialize() {
		Exit.exit("GameMap.java: figure out ");
	}
	
	public ServerEntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void update(KeysPressed pressed[], float delta) {
		
		accumulator += delta;
		while (accumulator >= dt)
		{
			world.step(dt, 6, 2);
			entityManager.updateAll(pressed);
            accumulator -= dt;
		}
		
	}
	
}





