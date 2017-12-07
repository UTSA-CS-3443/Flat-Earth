package client.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


import utilities.ParseMap.MapDetails;

/**
 * for the animatins of dying's flame
 * 
 * @author mauricio
 *
 */
public class DeadFlameAnimation {

	/**
	 * list of flames
	 */
	static ArrayList<Sprite> flames;
	
	// hard coded
	
	/**
	 * initializes the flames
	 */
	public static void initialize() {
		flames = new ArrayList<Sprite>();
		for (int i = 1; i < 5; i++) {
			Sprite s = new Sprite(new Texture("flameAnimation/" + i + ".png"));
			s.setSize(s.getWidth() * (MapDetails.SCALE), s.getHeight() * (MapDetails.SCALE));
			flames.add(s);
		}
	}
	
	/**
	 * @param i what index of flame
	 * @return
	 */
	public static Sprite getFlame(int i) {
		return flames.get(i % 4);
	}
	
}
