package communicators.clientNetworks;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import communicators.clientToServer.KeyboardState;
import communicators.serverNetworks.InitialPacketsInfo;
import communicators.serverNetworks.InitialServerPacket;
import communicators.serverToClient.GameState;
import utilities.Settings;
import utilities.Sys;

public class GameClient {

	Client client;
	Kryo kryo;
	
	ExecutorService es;
	
	Settings s;
	
	boolean initialized;
	
	InitialClientListener initialClientListener;
	ClientGameSendThread gameSendThread;
	
	public GameClient(Settings s) {
		this.s = s;
	}
	
	public void initialize() {
		client = new Client();
		es = Executors.newFixedThreadPool(1);
		initialized = true;
	}
	/*------------- initial connection methods--------------*/
	public void startInitialClient(String host, InitialClientPacket sendPacket) {
		Sys.print("Starting initial client...");
		if (!this.initialized)
			this.initialize();
		
		this.client.start();
		
		try{
			client.connect(5000, host, s.connectionSettings.serverTcpPort, s.connectionSettings.serverUdpPort); //Wait time, IP_address, tcp, udp
						//Some games have standardized ports that they always connect to
						//so maybe we can do that
		} catch(IOException e){
			e.printStackTrace();
			Sys.exit("GameCleint : failed to connect");
		}
		this.kryo = client.getKryo();
		
		kryo.register(InitialClientPacket.class);
		kryo.register(InitialServerPacket.class);
		kryo.register(InitialPacketsInfo.class);
		kryo.register(int[].class);
		
		
		// send 
		// wait to receive
		client.sendTCP(sendPacket);
		
		this.initialClientListener = new InitialClientListener();
		client.addListener(initialClientListener);
		
	}
	
	public boolean received() {
		return this.initialClientListener.received();
	}
	
	public InitialServerPacket endInitialClient() {
		this.client.removeListener(this.initialClientListener);
		//client.stop();
		return this.initialClientListener.getServerPacket();
	}
	
	
	/*------------------ game connectino methods --------------------*/
	
	public void startGameClient(Settings s, GameState gs, KeyboardState ks) {
		if (!this.initialized)
			this.initialize();
		this.client.start();
		this.kryo.register(KeyboardState.class);
		kryo.register(GameState.class);
		client.addListener(new GameClientListener(gs));
		this.gameSendThread = new ClientGameSendThread(ks, this.client);
		es.execute(gameSendThread);
	}
	
	public void endGameClient() {
		this.gameSendThread.kill();
		es.shutdownNow();
		client.stop();
	}
	
}































