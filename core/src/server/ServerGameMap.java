package server;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import communicators.clientToServer.KeysPressed;
import server.entities.ServerEntityManager;
import server.entities.ServerKnight;
import server.entities.ServerNpc;
import server.entities.ServerSpawner;
import utilities.Sys;
import utilities.ParseMap.Beacon;
import utilities.ParseMap.MapDetails;
import utilities.ParseMap.PolygonBody;

public class ServerGameMap {

	public float length;
	public float width;

	private int playerCount;
	
	private ServerEntityManager entityManager;
	
	private float accumulator;
	private float dt = 0.0133f;
	
	MapDetails details;
	
	private World world;
	
	
	public ServerGameMap(MapDetails details, int playerCount) {
		this.world = new World(new Vector2(0, 0), false);
		world.setContactListener(new CollisionListener());
		
		this.playerCount = playerCount;
		this.details = details;
		
		entityManager = new ServerEntityManager();
	}
	
	public void initialize() {		
		// spawn the first few beacons as players. spawn the rest as npcs/enemies
		// hard coding just spawning a Knight for now
		ArrayList<Beacon> beacons = details.getBeacons();
		int i;
		for (i = 0; i < this.playerCount; i++) {
			entityManager.add(ServerSpawner.spawnPlayer(ServerKnight.class, beacons.get(i).getX()*details.SCALE, beacons.get(i).getY()*details.SCALE, world));
		}
		for(i = playerCount; i < details.getBeacons().size(); i++) { // will eventuall need to spawn the approriate type of npc, that will
			// be set in the .pp file TODO
			entityManager.add(ServerSpawner.spawnNpc(ServerNpc.class, beacons.get(i).getX()*details.SCALE, beacons.get(i).getY()*details.SCALE, world));
		}
		// TODO now initialize polygons]
		for (PolygonBody pb : details.getPolygonBodies()) {
			if(pb.getVectors().size() > 8)
				Sys.print("ServerGameMap: initializing game holes: More than 8 vectors, wont work with libgdx");
			else 
				entityManager.addHole(ServerSpawner.spawnHole(pb.getScaledArray(), world));
		}
	}
	
	public ServerEntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void update(KeysPressed pressed[], float delta) {
		//System.out.println("serverGameMap: this uopdate is called---------");
		//accumulator += delta;
		//while (accumulator >= dt)
		//{
		//Sys.print("hahahahah");
		world.step(dt, 6, 2);
		entityManager.updateAll(pressed);
        accumulator -= dt;
		//}
		
	}
	
}
























