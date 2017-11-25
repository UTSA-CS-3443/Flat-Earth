package client.skills;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.ParseMap.MapDetails;

public class ClientHealthBars {
	
	// hardcoded af just to make ash mad
	// also just lazy
	
	protected static ArrayList<Sprite> bars;
	
	public static void initialize() {
		bars = new ArrayList<Sprite>();
		for (int i = 0; i <= 100; i += 5) {
			Sprite bar = new Sprite(new Texture("healthBars/" + i + ".png"));
			bar.setSize(bar.getWidth() * (MapDetails.SCALE / 1.2f), bar.getHeight() * (MapDetails.SCALE / 1.2f));
			bars.add(bar);
		}
	}
	
	public static Sprite getAppropriateHealthBar(float percent) {
		if (percent < 0)
			return bars.get(0);
		int index = (int)percent / 5;
		return bars.get(index);
	}
	
}
