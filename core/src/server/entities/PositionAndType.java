package server.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * you positiona nd type, for npc ai
 * 
 * @author mauricio
 *
 */
public class PositionAndType {

	/**
	 * position
	 */
	Vector2 vector;
	/**
	 * type
	 */
	CharacterType type;
	
	/**
	 * @param v
	 * @param t
	 */
	public PositionAndType(Vector2 v, CharacterType t) {
		vector = v;
		type = t;
	}
	
}
