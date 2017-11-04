package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

public class NpcWizard extends Npc {

	protected NpcWizard(Body body, int id) {
		super(CharacterType.WIZARD, body, id);
	}

}
