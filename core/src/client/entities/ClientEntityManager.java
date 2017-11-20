package client.entities;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import client.Game;
import communicators.serverToClient.CharacterState;
import utilities.Sys;
//import zzzztrash.entities.characters.Character;

public class ClientEntityManager {

	private ArrayList<ClientCharacter> charactersUpdateOrder;
	private ArrayList<ClientCharacter> charactersYOrder;
	
//	private ShapeRenderer shapes;
	
	public ClientEntityManager() {
		charactersUpdateOrder = new ArrayList<ClientCharacter>();
		charactersYOrder = new ArrayList<ClientCharacter>();
//		shapes = new ShapeRenderer();
//		shapes.setAutoShapeType(true);
//		shapes.setColor(.5f,  .5f, .5f, 1);
	}
	
	public void add (ClientCharacter character) {
		//Exit.exit("ClientEntityManager: nothing is happening here");
		charactersUpdateOrder.add(character);
		charactersYOrder.add(character);
	}
	
	
	
	public void updateAll(float delta, CharacterState cs[]) {
		for (int i = 0; i < cs.length; i++)
			charactersUpdateOrder.get(i).update(delta, cs[i]);
	}
	
	@SuppressWarnings("unchecked")
	public void drawAll(SpriteBatch batch) {
		Collections.sort(charactersYOrder);
//		shapes.setProjectionMatrix(Game.cam.combined);
		
		for (ClientCharacter c : charactersYOrder) {
			c.getFrame().draw(batch);
		}
		
		
//		batch.end();
//		shapes.begin();
//		for (ClientCharacter c : charactersYOrder)
//			shapes.circle(c.x, c.y, .5f);
//		shapes.end();
//		batch.begin();
	}
	
}
