package com.flatearth.desktop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flatearth.Game;

import controller.Logic;
import startGui.Launcher;

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
		// create thread pool
		es = Executors.newCachedThreadPool();
		s = new Settings();
		// TODO this should be set in the javafx, but for now it's just hardcoded
		cs = new ConnectionSettings("localhost", 9876, 9877);
		// launch the javafx
		// sets settings object, also creates handshake for connection settings
		Launcher.launchGui(args);
		//s.chosenState = 2; // TODO THIS IS HERE
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
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
		
		// Launch the logic thread
		es.execute(new Logic(ks, gs));
		
		// Launch the client part (view)
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, ks), config);
		es.shutdown(); // TODO i don't know if this is actually ending the threads or not. Might have to do that
    					// GameOver.gameOver thing in the while() of the threads
	}
	
	public static void clientPlay(Settings s, ConnectionSettings cs) {
		
		// create the GameState and KayboardState
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
		
		// Launch the sending and recieving threads for the client
		es.execute(new ClientSender(cs, ks));
		es.execute(new ClientReceiver(cs, gs));
		
		// Create the game object
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(s, gs, ks), config);
    	
		es.shutdown(); // same todo from above method
		
	}
	
	// check
	public static void hostPlay(Settings s, ConnectionSettings cs) {
		
		// GameState and Kayboard state for server
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
		
		//Launch server, server send thread, and server receiver thread
		es.execute(new Logic(ks, gs));
		es.execute(new ServerReceiver(ks, cs));
		es.execute(new ServerSender(gs, cs));
		
		// Launch the client. this client should be connecting to local host
		clientPlay(s, cs);// already calls es.shutdown(), if that even does anything
		
	}
	
	public static void test() {}

	public static void test2(){}
}































