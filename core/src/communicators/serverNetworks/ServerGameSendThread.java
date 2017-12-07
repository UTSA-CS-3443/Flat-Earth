package communicators.serverNetworks;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import communicators.serverToClient.GameState;

public class ServerGameSendThread implements Runnable {

	boolean running = true;
	ArrayList<Connection> connections;
	GameState gs;
	Server server;
	
	public ServerGameSendThread(GameState gs, Server server, ArrayList<Connection> connections) {
		// TODO Auto-generated constructor stub
		this.gs = gs;
		this.server = server;
		this.connections = connections;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			for (Connection c : this.connections) {
				c.sendUDP(this.gs);
			}
			//Sys.sleep(1, "Error sleeping in ServerGameSendThread"); // probably take this out
		}
		
	}

	public void kill() {
		running = false;
	}

}
