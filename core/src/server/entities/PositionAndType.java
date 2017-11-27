package server.entities;

import com.badlogic.gdx.math.Vector2;

public class PositionAndType {

	Vector2 vector;
	CharacterType type;
	
	public PositionAndType(Vector2 v, CharacterType t) {
		vector = v;
		type = t;
	}
	
}
