package entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Knight extends Character
{

	protected Knight(Body body)
	{
		super(CharacterType.KNIGHT, body);
	}

}
