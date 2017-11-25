package client.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.ActionTrigger;
import utilities.Sys;

public class ClientNpc extends ClientCharacter {

	protected ClientNpc() {
		super(CharacterType.WIZARD);
		//Sys.exit("clientnpc.java: yeah, this isnt a izard");
	}

	
	public Sprite getFrame()
	{		
		//if attacking
		//return getAttackFrame();
		Sprite s = null;
		if (this.trigger == ActionTrigger.ATTACKING)
			s = getAttackFrame();
		else if(!this.charMovement)
			s = getStandFrame();
		else
			s = getWalkFrame(); 
		s.setPosition(this.x - s.getWidth() / 2, this.y);
		s.setColor(Color.FIREBRICK);
		return s;
	}
	
}
