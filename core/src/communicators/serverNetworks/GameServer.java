package communicators.serverNetworks;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import communicators.clientNetworks.InitialClientPacket;
import communicators.clientToServer.KeyboardState;
import communicators.serverToClient.GameState;
import utilities.Settings;
import utilities.Sys;

public class GameServer {

	Server server;
	ArrayList<Connection> clientConnections; // set in the javafx
	Kryo kryo;
	
	ExecutorService es;

	Settings s;
	
	InitialServerListener initialServerListener;
	ServerGameSendThread gameSendThread;
	
	boolean initialized = false;
	
	public GameServer(Settings s) {
		this.s = s;
	}
	
	public void initialize() {
		server = new Server();
		es = Executors.newFixedThreadPool(1);
		initialized = true;
	}
	
	/*-------------------------initial server connection functions------------------------*/
	
	public void startInitialServer() {
		Sys.print("Starting initial server...");
		if (!this.initialized)
			this.initialize();
		
		this.server.start();
		try {
			server.bind(s.connectionSettings.serverTcpPort, s.connectionSettings.serverUdpPort);
		} catch(IOException e){
	  		e.printStackTrace();
	  		//Also open window here saying connection failed.
	  		Sys.exit("in GameServer.java : connection failed");
		}
		
		this.kryo = server.getKryo();
		
		kryo.register(InitialClientPacket.class);
		kryo.register(InitialServerPacket.class);
		kryo.register(InitialPacketsInfo.class);
		kryo.register(int[].class);
		
		this.initialServerListener = new InitialServerListener();
		server.addListener(initialServerListener);
	}
	
	public void endInititalServer() { // TODO probably still need to return something, and IntiialServerPacket i guess
		server.removeListener(this.initialServerListener);
		for (int i = 0; i < initialServerListener.connections.size(); i++)
			initialServerListener.connections.get(i).sendTCP(new InitialServerPacket(i, initialServerListener.getPacketsInfo()));
		this.clientConnections = initialServerListener.connections;
		//server.stop(); //??
	}
	
	public int getConnectionCount() {
		return this.initialServerListener.getConnectionCount();
	}
	
	/*-------------------------game part connection functions--------------------------*/
	
	public void startGameServer(Settings s, GameState gs, ArrayList<KeyboardState> kss) {
		if (!this.initialized)
			this.initialize();
		server.start();
		kryo.register(KeyboardState.class);
		kryo.register(GameState.class);
		server.addListener(new GameServerListener(kss));
		this.gameSendThread = new ServerGameSendThread(gs, this.server, this.clientConnections);
		es.execute(gameSendThread);
	}
	
	public void endGameServer() {
		this.gameSendThread.kill(); // should call notifyAll();
		es.shutdownNow(); // maybe, maybe not
		server.stop();
	}
	
}




































