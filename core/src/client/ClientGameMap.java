package client;

import communicators.serverToClient.CharacterState;
import communicators.serverToClient.GameState;
import utilities.Exit;
import utilities.ParseMap.MapDetails;
//import zzzztrash.com.flatearth.Game;
import client.entities.ClientCharacter;
import client.entities.ClientEntityManager;

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
	public ClientGameMap(MapDetails details, int clientId) {
		entityManager = new ClientEntityManager();
		this.details = details;
		this.initialize();
	}
	
	// needs to get all the details from the mapdetails
	public void initialize() {
		Exit.exit("GameMap.java: figure out ");
		this.mapSprite = new Sprite(Game.atlas.findRegion(details.mapName));
		// need to store a reference to the Character for this client,
		// for the orthographic camera

		// also, need to store the client's character in the right position of
		// the list, depending on the client id they receive from server
		
		// add the entities to the manager. Add this player in position clientId
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


















