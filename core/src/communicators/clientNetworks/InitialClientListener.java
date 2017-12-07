package communicators.clientNetworks;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import communicators.serverNetworks.InitialServerPacket;

public class InitialClientListener extends Listener {

	boolean received = false;
	InitialServerPacket packet;
	
	public InitialClientListener () {
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof InitialServerPacket){
			this.packet = (InitialServerPacket)object;
			this.received = true;
		}
	}
	
	public boolean received() {
		return this.received;
	}

	public InitialServerPacket getServerPacket() {
		return this.packet;
	}

}
