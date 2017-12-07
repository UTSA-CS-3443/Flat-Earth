package communicators.serverNetworks;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import communicators.clientToServer.KeyboardState;

public class GameServerListener extends Listener {

	ArrayList<KeyboardState> kss;
	
	public GameServerListener(ArrayList<KeyboardState> kss) {
		this.kss = kss;
	}

	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof KeyboardState){
			KeyboardState ks = (KeyboardState)object;
			this.kss.get(ks.id).updateData(ks);
		}
	}

	
}
