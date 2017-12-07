package client.skills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.ParseMap.MapDetails;

/**
 * for client skill animations
 * 
 * @author mauricio
 *
 */
public class ClientSkillAnimations {
	
	/**
	 * arrow
	 */
	static Sprite arrowSprite = null;
	/**
	 * firball
	 */
	static Sprite fireBallSprite = null;
	
	/**
	 * @return
	 */
	public static Sprite getArrow() {
		if (arrowSprite == null) {
			arrowSprite = new Sprite(new Texture("arrow.png"));
			arrowSprite.setSize(arrowSprite.getWidth() * MapDetails.SCALE, arrowSprite.getHeight() * MapDetails.SCALE);
		}
		return arrowSprite;
	}
	
	/**
	 * @return
	 */
	public static Sprite getFireBall() {
		if (fireBallSprite == null) {
			fireBallSprite = new Sprite(new Texture("Fireball.png"));
			fireBallSprite.setSize(fireBallSprite.getWidth() * MapDetails.SCALE, fireBallSprite.getHeight() * MapDetails.SCALE);
		}
		return fireBallSprite;
	}
	
}
