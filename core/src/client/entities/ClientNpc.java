package client.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import communicators.ActionTrigger;
import utilities.Sys;

public class ClientNpc extends ClientCharacter {

	protected ClientNpc() {
		super(CharacterType.WIZARD);
		//Sys.exit("clientnpc.java: yeah, this isnt a izard");
	}
	
}
