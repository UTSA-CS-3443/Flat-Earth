package client.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.Sys;
import utilities.ParseMap.MapDetails;

public class DeadFlameAnimation {

	static ArrayList<Sprite> flames;
	
	// hard coded
	
	public static void initialize() {
		flames = new ArrayList<Sprite>();
		for (int i = 1; i < 5; i++) {
			Sprite s = new Sprite(new Texture("flameAnimation/" + i + ".png"));
			s.setSize(s.getWidth() * (MapDetails.SCALE), s.getHeight() * (MapDetails.SCALE));
			flames.add(s);
		}
	}
	
	public static Sprite getFlame(int i) {
		return flames.get(i % 4);
	}
	
}
