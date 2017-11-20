package client.entities;

import utilities.Sys;

public class ClientNpc extends ClientCharacter {

	protected ClientNpc() {
		super(CharacterType.WIZARD);
		Sys.exit("clientnpc.java: yeah, this isnt a izard");
	}

}
