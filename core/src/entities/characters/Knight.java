package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Knight extends Player
{

	protected Knight(Body body, int id)
	{
		super(CharacterType.KNIGHT, body, id);
	}

}
