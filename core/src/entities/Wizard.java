package entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Wizard extends Character
{
	protected Wizard(Body body)
	{
		super(CharacterType.WIZARD, body);
	}

}
