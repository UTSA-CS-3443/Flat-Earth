package entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Archer extends Character
{
	protected Archer(Body body)
	{
		super(CharacterType.ARCHER, body);
	}

}
