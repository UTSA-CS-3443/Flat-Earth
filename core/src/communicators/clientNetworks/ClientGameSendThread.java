package communicators.clientNetworks;

import com.esotericsoftware.kryonet.Client;

import communicators.clientToServer.KeyboardState;

public class ClientGameSendThread implements Runnable {

	Client client;
	KeyboardState ks;
	boolean running = true;
	
	public ClientGameSendThread(KeyboardState ks, Client client) {
		this.ks = ks;
	}

	@Override
	public void run() {
		
		while(running) {
			client.sendUDP(this.ks);
			//Sys.sleep(1, "ClientGameSendThread, sleep error");
		}
		
	}

	public void kill() {
		this.running = false;
	}

}
