package client.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.Sys;

public class ClientNpc extends ClientCharacter {

	protected ClientNpc() {
		super(CharacterType.WIZARD);
		Sys.exit("clientnpc.java: yeah, this isnt a izard");
	}
	
	// doing this in here so that i can change the color, since the enemies are just gonna be wizards
	@Override
	public Sprite getFrame()
	{		
		//if attacking
		//return getAttackFrame();
		Sprite s = null;
		if(!this.charMovement)
			s = super.getStandFrame();
		else
			s = getWalkFrame(); 
		s.setPosition(this.x - s.getWidth() / 2, this.y);
		s.setColor(255, 255, 255, 255);
		return s;
	}

}
