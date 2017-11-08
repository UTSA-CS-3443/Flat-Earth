package zzzztrash.entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Wizard extends Player
{
	protected Wizard(Body body, int id)
	{
		super(CharacterType.WIZARD, body, id);
	}

}
