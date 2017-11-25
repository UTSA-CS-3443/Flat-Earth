package client.skills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.ParseMap.MapDetails;

// this is only temporary, idk
// don't vomit at this class

public class ClientSkillAnimations {
	
	static Sprite arrowSprite = null;
	static Sprite fireBallSprite = null;
	
	public static Sprite getArrow() {
		if (arrowSprite == null) {
			arrowSprite = new Sprite(new Texture("arrow.png"));
			arrowSprite.setSize(arrowSprite.getWidth() * MapDetails.SCALE, arrowSprite.getHeight() * MapDetails.SCALE);
		}
		return arrowSprite;
	}
	
	public static Sprite getFireBall() {
		if (fireBallSprite == null) {
			fireBallSprite = new Sprite(new Texture("Fireball.png"));
			fireBallSprite.setSize(fireBallSprite.getWidth() * MapDetails.SCALE, fireBallSprite.getHeight() * MapDetails.SCALE);
		}
		return fireBallSprite;
	}
	
}
