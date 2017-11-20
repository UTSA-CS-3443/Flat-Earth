package client;

import communicators.serverToClient.CharacterState;
import communicators.serverToClient.GameState;
import utilities.Sys;
import utilities.Settings;
import utilities.ParseMap.MapDetails;
import client.entities.ClientArcher;
//import zzzztrash.com.flatearth.Game;
import client.entities.ClientCharacter;
import client.entities.ClientEntityManager;
import client.entities.ClientKnight;
import client.entities.ClientNpc;
import client.entities.ClientSpawner;
import client.entities.ClientWizard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ClientGameMap {

	public ClientEntityManager entityManager;
	
	public MapDetails details;
	
	private Sprite mapSprite;
	
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
		this.initialize(settings);
	}
	
	// needs to get all the details from the mapdetails
	public void initialize(Settings settings) {
		//Exit.exit("GameMap.java: figure out ");
		//this.mapSprite = new Sprite(Game.atlas.findRegion(details.mapName));
		this.mapSprite = new Sprite(new Texture(details.mapName));
		// need to store a reference to the Character for this client,
		// for the orthographic camera
		switch(settings.characterType) {
		case 0:
			this.player = ClientSpawner.spawn(ClientKnight.class, details.getBeacons().get(settings.clientId).getX(), details.getBeacons().get(settings.clientId).getY());
			break;
		case 1:
			this.player = ClientSpawner.spawn(ClientArcher.class, details.getBeacons().get(settings.clientId).getX(), details.getBeacons().get(settings.clientId).getY());
			break;
		case 2:
			this.player = ClientSpawner.spawn(ClientWizard.class, details.getBeacons().get(settings.clientId).getX(), details.getBeacons().get(settings.clientId).getY());
			break;
		}
		// spawn the players
		for (int i = 0; i < settings.playerCount; i++) {
			if (i == settings.clientId) {
				entityManager.add(this.player);
			} else { // just spawning wizards for now, but will eventaully have to be what the players are (should be sent over the server, in the settings object)
				entityManager.add(ClientSpawner.spawn(ClientWizard.class, details.getBeacons().get(i).getX(), details.getBeacons().get(i).getY()));
			}
		}
		for (int i = settings.playerCount; i < details.getBeacons().size(); i++) { // TODO spawning wizards for now, needs to be specific npcs later
				entityManager.add(ClientSpawner.spawn(ClientWizard.class, details.getBeacons().get(i).getX(), details.getBeacons().get(i).getY()));
		}
		
		// here would be where we spawn all the trees or whatever but that's not in the MapDetails yet
	}
	
	public ClientEntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void update(float delta, GameState gs) {
		CharacterState cs[] = gs.getCharacterStates();
		entityManager.updateAll(delta, cs);
	}
	
	public void draw(SpriteBatch batch) {
		this.mapSprite.draw(batch);
		this.entityManager.drawAll(batch);
	}
	
	public ClientCharacter getPlayer() {
		return this.player;
	}

	
}


















