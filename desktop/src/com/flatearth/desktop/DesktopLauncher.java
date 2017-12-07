package com.flatearth.desktop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.Game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


import communicators.clientNetworks.GameClient;
import communicators.clientNetworks.InitialClientPacket;
import communicators.clientToServer.KeyboardState;
import communicators.serverNetworks.GameServer;
import communicators.serverNetworks.InitialPacketsInfo;
import communicators.serverNetworks.InitialServerPacket;
import communicators.serverToClient.GameState;
import server.Logic;
import startGui.Launcher;
import utilities.*;
import utilities.ParseMap.MapDetails;

/**
 * Main class. Call javafx, which sets the settings, then starts the game.
 * also has a terminal mode for the set up (instead of javafx)
 * 
 * @author mauricio
 *
 */

public class DesktopLauncher {
	
	/**
	 * thread pool for launching the threads
	 */
	private static ExecutorService es; 
	/**
	 * general settings, will be set by javafx
	 */
	public static Settings s;
	/**
	 * for host play
	 */
	static GameServer server;
	/**
	 * for client play
	 */
	static GameClient client;
	/**
	 * for terminal mode
	 */
	static boolean terminalMode = false;
	/**
	 * calls javafx, which sets the settings, calls appropriate start method
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		//printMessages();
		// create thread pool
		es = Executors.newCachedThreadPool();
		s = new Settings();
		// TODO this should be set in the javafx, but for now it's just hardcoded
		s.connectionSettings = new ConnectionSettings();//"localhost", 9876, 9877);
		// launch the javafx
		// sets settings object, also creates handshake for connection settings
		server = new GameServer(s);
		client = new GameClient(s);
		//Launcher.launchGui(args);
		//s.characterType = 1;
		//s.chosenState = 0;
		if (terminalMode) {
			Scanner console = new Scanner(System.in);
			Sys.print("Enter 0 for solo play, 1 for Client play, 2 for host play");
			s.chosenState = console.nextInt();
		} else {
			Launcher.launchGui(args);
		}
		switch(s.chosenState) {
		case 0: // currently runs even if they close the window instead of hitting start. fix that
			soloPlay();
			break;
		case 1:
			if (terminalMode)
				clientTerminalLauncher();
			Sys.exit("reached end of initial client");
			clientPlay(null);
			break;
		case 2: 
			if (terminalMode)
				serverTerminalLauncher();
			Sys.exit("reached end of initial server");
			hostPlay();
			break;
		default:
			Sys.print("nothing");
		}
	}
	/**
	 * launches a Game object(view) and Logic object(control)
	 */
	public static void soloPlay() {
		MapDetails details = null;
		try {
			details = new MapDetails("map.pp");
		} catch (Exception e) {
			e.printStackTrace();
			Sys.exit("DeskptopLauncher: MapDetails failed");
		}
		// game state needs to know how many things are gonna be on screen. For now that's the beacon amt
		GameState gs = new GameState(details.getBeacons().size());
		
		ArrayList<KeyboardState> kss = new ArrayList<KeyboardState>(); // has to be arraylist for logic thread
		kss.add(new KeyboardState(s.clientId));
		
		es.execute(new Logic(s, gs, kss, details));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, kss.get(0), details), config);
	}

	/**
	 * starts the server, launches the Logic object, launches a client
	 */
	public static void hostPlay() {
		MapDetails details = null;
		try {
			details = new MapDetails("map.pp");
		} catch (Exception e) {
			e.printStackTrace();
			Sys.exit("DeskptopLauncher: MapDetails failed");
		}
		
		
		GameState gs = new GameState(details.getBeacons().size());
		ArrayList<KeyboardState> kss = new ArrayList<KeyboardState>();
		
		server.startGameServer(s, gs, kss); // proabbaly need to pass this more stuff
		
		es.execute(new Logic(s, gs, kss, details)); // client side prediction will launch 2 instances of the server
													// if youre hosting
		clientPlay(details);
		
	}
	
	/**
	 * Starts the client, launches the Game object
	 * @param details
	 */
	public static void clientPlay(MapDetails details) {
		
		MapDetails d = null; // this is temporary, eventually wont be like this with client side prediction
		if (details != null) {
			d = details;
		} else {
			try {
				details = new MapDetails("map.pp");
			} catch (Exception e) {
				e.printStackTrace();
				Sys.exit("DeskptopLauncher: MapDetails failed");
			}
		}
		
		GameState gs = new GameState(details.getBeacons().size());
		KeyboardState ks = new KeyboardState(s.clientId); // this is assuming no client side prediction
		
		client.startGameClient(s, gs, ks);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, ks, d), config);
		
	}
	
	
/*---------------------------------------------------terminal launchers-----------------------------------------------*/	
	
	/*-----client------*/
	/**
	 * for terminal mode, simulates javafx
	 * 
	 *  something like this. this should pause the javafx with that while loop, and entirely 
	 * just wait for the server to reply. bad design tho
	 * @param host IP
	 * @param currentCharChoice charactert type chosen
	 */
	public static void connectButtonHit(String host, int currentCharChoice) {
		// parameters wouldnt get passed in here, but taken from the gui
		
		InitialClientPacket packet = new InitialClientPacket(currentCharChoice);
		
		DesktopLauncher.client.startInitialClient(host, packet);
		
		while(!DesktopLauncher.client.received()) {
			Sys.sleep(50, "Javafx Client something : Error sleeping");
		}
		
		InitialServerPacket receivedPacket = DesktopLauncher.client.endInitialClient();
		
		DesktopLauncher.s.chosenState = 1;
		DesktopLauncher.s.clientId = receivedPacket.id;
		DesktopLauncher.s.playerCount = receivedPacket.otherClientsInfo.chosenCharacters.length;
		DesktopLauncher.s.allCharacterTypes = receivedPacket.otherClientsInfo.chosenCharacters;
//		DesktopLauncher.s.dpad = 
//		DesktopLauncher.s.controller = 
//		DesktopLauncher.s.music = 
		// probably some more stuff
		// kill javafx
		
	}
	/**
	 * for terminal more, simulates javafx
	 */
	public static void clientTerminalLauncher() {
		Scanner console = new Scanner(System.in);
		Sys.print("Enter ip: ");
		String ip = console.nextLine();
		console.close();
		connectButtonHit(ip, 0);
	}
	
	/*----server stuff-----*/
	/**
	 * for terminal mode, simulates javafx
	 */
	public static void startListeningButtonHit() {
		
		DesktopLauncher.server.startInitialServer();
		InitialClientPacket packet = new InitialClientPacket(0); // hardcoded 0 for now, no bueno. this is chosenState
		DesktopLauncher.client.startInitialClient("localhost", packet);
		
	}
	
	/**
	 * for termninal more, simulatesjavafx
	 * 
	 * returning int for now, just for the terminal stuff
	 * @return nummber of connections made on server
	 */
	public static int countButtonHit() {
		
		return /*SomeJavafxLabel.value = */DesktopLauncher.server.getConnectionCount();
		
	}
	
	/**
	 * for terminal mode, simulates javafx
	 */
	public static void startButtonHit() {
		
		int count = server.getConnectionCount(); // this is ONYL for the the hardcoding part, for the local host bs
		DesktopLauncher.server.endInititalServer();
		InitialServerPacket packet = DesktopLauncher.client.endInitialClient();
		if(packet == null) {
			Sys.print("Packet was null so fabiracating one");
			packet = new InitialServerPacket(0, new InitialPacketsInfo(new int[count])); // hard code this for now cause i think the error is the local host stuff
		}
		DesktopLauncher.s.clientId = packet.id;
		DesktopLauncher.s.playerCount = packet.otherClientsInfo.chosenCharacters.length;
		DesktopLauncher.s.allCharacterTypes = packet.otherClientsInfo.chosenCharacters;
		DesktopLauncher.s.chosenState = 2; // 2 for host play
//		DesktopLauncher.s.dpad = 
//		DesktopLauncher.s.controller = 
//		DesktopLauncher.s.music = 
		
	}
	/**
	 * for terminal mode, simulates javafx. launches the server terminal
	 */
	public static void serverTerminalLauncher() {
		Scanner console = new Scanner(System.in);
		startListeningButtonHit();
		Sys.print("Listening...");
		Sys.print("Commands: count , start");
		while(true) {
			String command = console.nextLine();
			if (command.equals("count")) {
				Sys.print("Current count: " + countButtonHit());
			} else if (command.equals("start")) {
				break;
			} else {
				Sys.print("Invalid command");
			}
		}
		startButtonHit();
		console.close();
	}
	
	
	//TODO all of these
	/**
	 * just for messages
	 */
	public static void printMessages() {
		Sys.print("------");
		Sys.print("Currently not getting map from the map atlas. Check ClientgameMap.java");
		Sys.print("ServerGameMap, took out the while loop that nobody knows how it works or what it does but we should put it back. it was messing things up though");
		Sys.print("Eventually get rid of console prints and exits");
		Sys.print("gotta get rid of the sleep in logic.java");
		Sys.print("the weird jump when falling off is in serverCharacter, it's commented");
		Sys.print("------");
	}
	
}































