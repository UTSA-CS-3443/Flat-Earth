package communicators.clientNetworks;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import communicators.serverToClient.GameState;

public class GameClientListener extends Listener {

	GameState gs;
	
	public GameClientListener(GameState gs) {
		this.gs = gs;
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof GameState){
			this.gs.update((GameState)object);
		}
	}

}
