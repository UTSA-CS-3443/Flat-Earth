package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Archer extends Player {
	public Archer(Body body)
	{
		super(CharacterType.ARCHER, body);
	}

}
