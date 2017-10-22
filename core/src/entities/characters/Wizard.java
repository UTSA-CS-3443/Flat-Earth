package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Wizard extends Player {
	public Wizard(Body body)
	{
		super(CharacterType.WIZARD, body);
	}

}
