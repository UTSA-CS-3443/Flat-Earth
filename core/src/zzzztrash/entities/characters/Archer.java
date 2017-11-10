package zzzztrash.entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class Archer extends Player
{
	protected Archer(Body body, int id)
	{
		super(CharacterType.ARCHER, body, id);
	}

}
