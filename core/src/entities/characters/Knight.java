package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Knight extends Player {

	public Knight(Body body)
	{
		super(CharacterType.KNIGHT, body);
	}

}
