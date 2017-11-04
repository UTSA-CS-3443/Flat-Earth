package com.flatearth.desktop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flatearth.Game;

import controller.Logic;
//import startGui.Launcher;

import utilities.*;

import communicators.*;

public class DesktopLauncher {
	
	//thread pool for launching the threads
	private static ExecutorService es; 
	// settings for connections between client and server, will be sset by javafx
	public static ConnectionSettings cs;
	// general settings, will be set by javafx
	public static Settings s;
	
	public static void main (String[] args) {
		printMessages();
		// create thread pool
		es = Executors.newCachedThreadPool();
		s = new Settings();
		// TODO this should be set in the javafx, but for now it's just hardcoded
		cs = new ConnectionSettings("localhost", 9876, 9877);
		// launch the javafx
		// sets settings object, also creates handshake for connection settings
		//Launcher.launchGui(args);
		switch(s.chosenState) {
		case 0: // currently runs even if they close the window instead of hitting start. fix that
			soloPlay(s);
			break;
		case 1: 
			clientPlay(s, cs);
			break;
		case 2: 
			hostPlay(s, cs);
			break;
		}
	}

	public static void soloPlay(Settings s) {
		// create the GameState and KeyboardState objects
		GameState gs = new GameState(2); // TODO dont hard code this, has to coordinate
		// with client and server		// hadrcoded in 3 places here
		KeyboardState ks = new KeyboardState(0);
		
		// Launch the logic thread
		es.execute(new Logic(ks, gs));
		
		// Launch the client part (view)
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, ks, 0), config);
		es.shutdown(); // TODO i don't know if this is actually ending the threads or not. Might have to do that
    					// GameOver.gameOver thing in the while() of the threads
	}
	
	public static void clientPlay(Settings s, ConnectionSettings cs) {
		
		// create the GameState and KayboardState
		GameState gs = new GameState(2);  // TODO hardcoded for size
		// TODO hardcoded the singlular keybaord state, probably make some rapper function
		// for an array of them
		KeyboardState ks = new KeyboardState(0);
		
		// Launch the sending and recieving threads for the client
		es.execute(new ClientSender(cs, ks));
		es.execute(new ClientReceiver(cs, gs));
		
		// Create the game object
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, ks, 0), config);
    	
		es.shutdown(); // same todo from above method
		
	}
	
	// check
	public static void hostPlay(Settings s, ConnectionSettings cs) {
		
		// GameState and Kayboard state for server
		GameState gs = new GameState(2); // TODO hardcoded for size
		// TODO hardcoded the singlular keybaord state, probably make some rapper function
		// for an array of them
		KeyboardState ks = new KeyboardState(0);
		
		//Launch server, server send thread, and server receiver thread
		es.execute(new Logic(ks, gs));
		es.execute(new ServerReceiver(ks, cs));
		es.execute(new ServerSender(gs, cs));
		
		// Launch the client. this client should be connecting to local host
		clientPlay(s, cs);// already calls es.shutdown(), if that even does anything
		
	}
	
	
	public static void printMessages() {
		String msgs[] = {"DesktopLauncher: new GameState(1) will cause bug with enemies",
				"GameState: as a whole",
				"ClientReceiver: commented out the game state copy over, fix it",
				"Game (really everywhere): hardcoded in the Client id stuff. needs to be dynamic and decided" + 
				"by the server, and sent over before the javafx ends, and passed into new Game()",
				"SpawnGenerator: commented out the npc initilization, will be null",
				"change all start id's to 0",
				"Entity manager needs to work off one list, not two. currently only using the player one too",
				"Logic: GameState update not happening",
				"Launcher: un cardode the size of the gamestate",
				"ServerNpc: hardcoded",
		""};
		for (String msg : msgs)
			System.out.println(msg);
	}
	
}































