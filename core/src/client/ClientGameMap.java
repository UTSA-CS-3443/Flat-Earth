package client;

import communicators.serverToClient.CharacterState;
import communicators.serverToClient.GameState;
import communicators.serverToClient.SkillState;
import server.entities.ServerNpcArcher;
import server.entities.ServerNpcKnight;
import server.entities.ServerNpcWizard;
import server.entities.ServerSpawner;
import utilities.Sys;
import utilities.Settings;
import utilities.ParseMap.Beacon;
import utilities.ParseMap.MapDetails;
import utilities.ParseMap.PolygonBody;
import client.entities.ClientArcher;
//import zzzztrash.com.flatearth.Game;
import client.entities.ClientCharacter;
import client.entities.ClientEntityManager;
import client.entities.ClientKnight;
import client.entities.ClientNpc;
import client.entities.ClientSpawner;
import client.entities.ClientWizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ClientGameMap {
	
	// TODO for debugging, should eventually not be here
	public World world;
	
	public ClientEntityManager entityManager;
	
	public MapDetails details;
	
	private Sprite mapSprite;
	private Sprite spaceSprite;
		
	// a refernce to the player. This is still stored in the entity manager,
	// but we need this here for the orthographic camera
	private ClientCharacter player;
	
	// purpose for the client id is to store the player at that position in the
	// entity manager, cause that's the order the server will send over it's 
	// data
	// kinda weird that this has to take the settings but it's the only way the server
	// will communicate before the game actually starts
	public ClientGameMap(MapDetails details, Settings settings) {
		entityManager = new ClientEntityManager();
		this.details = details;		
		if (Game.debugFromStart) {
			this.world = new World(new Vector2(0, 0), false);
			this.world.setContactListener(new DebuggingContact());
		}
		this.initialize(settings);
	}
	
	// needs to get all the details from the mapdetails
	public void initialize(Settings settings) {
		//Exit.exit("GameMap.java: figure out ");
		//this.mapSprite = new Sprite(Game.atlas.findRegion(details.mapName));
		this.mapSprite = new Sprite(new Texture(details.mapName));
		this.mapSprite.setSize(mapSprite.getWidth()*MapDetails.SCALE, mapSprite.getHeight()*MapDetails.SCALE);
		
		this.spaceSprite = new Sprite(new Texture("space.png"));
		this.spaceSprite.setSize(50, 50);
		// need to store a reference to the Character for this client,
		// for the orthographic camera
		switch(settings.characterType) {
		case 0:
			this.player = ClientSpawner.spawn(ClientKnight.class, details.getBeacons().get(settings.clientId).getX()*details.SCALE, details.getBeacons().get(settings.clientId).getY()*details.SCALE);
			break;
		case 1:
			this.player = ClientSpawner.spawn(ClientArcher.class, details.getBeacons().get(settings.clientId).getX()*details.SCALE, details.getBeacons().get(settings.clientId).getY()*details.SCALE);
			break;
		case 2:
			this.player = ClientSpawner.spawn(ClientWizard.class, details.getBeacons().get(settings.clientId).getX()*details.SCALE, details.getBeacons().get(settings.clientId).getY()*details.SCALE);
			break;
		}
		// spawn the players
		for (int i = 0; i < settings.playerCount; i++) {
			if (i == settings.clientId) {
				entityManager.add(this.player);
			} else { // just spawning wizards for now, but will eventaully have to be what the players are (should be sent over the server, in the settings object)
				entityManager.add(ClientSpawner.spawn(ClientNpc.class, details.getBeacons().get(i).getX()*details.SCALE, details.getBeacons().get(i).getY()*details.SCALE));
			}
		}
		for (int i = settings.playerCount; i < details.getBeacons().size(); i++) { // TODO spawning wizards for now, needs to be specific npcs later
				//entityManager.add(ClientSpawner.spawn(ClientNpc.class, details.getBeacons().get(i).getX()*details.SCALE, details.getBeacons().get(i).getY()*details.SCALE));
			Beacon b = details.getBeacons().get(i);
			for(int k = 0; k < b.getProperties().size(); k++)
			{
				if(b.getProperties().get(k).type.equals("sprite"))
				{
					System.out.println("FOOK YAS");
					entityManager.add(new ClientGameMapSprite(b.getProperties().get(k).value, b.getX(), b.getY()));
				}
			}
			if(b.getProperties().get(0).value == null) {
				// this is to fix some bug with the way map details is parsing. gonna also be an archer server side
				entityManager.add(ClientSpawner.spawn(ClientArcher.class, b.getX()*details.SCALE, b.getY()*details.SCALE));
			} else if(b.getProperties().get(0).value.equals("\"knight\"")) {
				entityManager.add(ClientSpawner.spawn(ClientKnight.class, b.getX()*details.SCALE, b.getY()*details.SCALE));
			} else if(b.getProperties().get(0).value.equals("\"wizard\"")) {
				entityManager.add(ClientSpawner.spawn(ClientWizard.class, b.getX()*details.SCALE, b.getY()*details.SCALE));
			} else if(b.getProperties().get(0).value.equals("\"archer\"")) {
				entityManager.add(ClientSpawner.spawn(ClientArcher.class, b.getX()*details.SCALE, b.getY()*details.SCALE));
			} else {
				// this is to fix some bug with the way map details is parsing. gonna also be an archer server side
				entityManager.add(ClientSpawner.spawn(ClientArcher.class, b.getX()*details.SCALE, b.getY()*details.SCALE));
			}
		}
		
		// spanw the polygons, for debugging
		if (Game.debugFromStart) {
			for (PolygonBody pb : details.getPolygonBodies()) {
				if(pb.getVectors().size() > 8)
					Sys.print("ClientGameMap: initializing game holes: More than 8 vectors, wont work with libgdx");
				else 
					ServerSpawner.spawnHole(pb.getScaledArray(), world);
			}
		}
		
		// here would be where we spawn all the trees or whatever but that's not in the MapDetails yet
	}
	
	public ClientEntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void update(float delta, GameState gs) {
		CharacterState cs[] = gs.getCharacterStates();
		SkillState ss[] = gs.getSkillStates();
		entityManager.updateAll(delta, cs, ss);
	}
	
	public void draw(SpriteBatch batch) {
		this.spaceSprite.setCenter(this.player.x, this.player.y);
		this.spaceSprite.draw(batch);
		this.mapSprite.draw(batch);
		this.entityManager.drawAll(batch);
	}
	
	public ClientCharacter getPlayer() {
		return this.player;
	}

	
}


















