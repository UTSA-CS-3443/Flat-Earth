package communicators.serverNetworks;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import communicators.clientNetworks.InitialClientPacket;
import utilities.Sys;

public class InitialServerListener extends Listener {

	
	private int connectionCount = 0;
	ArrayList<InitialClientPacket> packets;
	ArrayList<Connection> connections;
	
	public InitialServerListener() {
		packets = new ArrayList<InitialClientPacket>();
		connections = new ArrayList<Connection>();
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof InitialClientPacket){
			packets.add((InitialClientPacket)object);
			connections.add(connection);
			this.connectionCount++;
		}
	}

	public InitialPacketsInfo getPacketsInfo() {		
		return new InitialPacketsInfo(connectionCount, this.packets);
	}

	public int getConnectionCount() {
		return connectionCount;
	}
	
}
