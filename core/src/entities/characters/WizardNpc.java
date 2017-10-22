package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

// mainly for testing purposes, probably wont be an actual enemy

public class WizardNpc extends Npc {

	public WizardNpc(Body body) {
		super(CharacterType.WIZARD, body);
	}
	
}
