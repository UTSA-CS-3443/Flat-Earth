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
	
	private static ExecutorService es; 
	public static ConnectionSettings cs;
	public static Settings s;
	
	public static void main (String[] args) {
		System.out.println("Main bug will be packet size. might jsut use kryonet");
		System.out.println("Also in settings object");
		es = Executors.newCachedThreadPool();
		s = new Settings();
		cs = new ConnectionSettings("localhost", 9876, 9877);
		Launcher.launchGui(args); // sets settings object, also creates handshake for connection settings
		s.chosenState = 2; // TODO THIS IS HERE
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
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
				
		es.execute(new Logic(ks, gs));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    	new LwjglApplication(new Game(s, gs, ks), config);
    	es.shutdown(); // TODO i don't know if this is actually ending the threads or not. Might have to do that
    					// GameOver.gameOver thing in the while() of the threads
	}
	
	public static void clientPlay(Settings s, ConnectionSettings cs) {
		
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
		
		es.execute(new ClientSender(cs, ks));
		es.execute(new ClientReceiver(cs, gs));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    	new LwjglApplication(new Game(s, gs, ks), config);
    	
    	es.shutdown();
		
	}
	
	// check
	public static void hostPlay(Settings s, ConnectionSettings cs) {
		
		GameState gs = new GameState();
		KeyboardState ks = new KeyboardState();
		
		es.execute(new Logic(ks, gs));
		es.execute(new ServerReceiver(ks, cs));
		es.execute(new ServerSender(gs, cs));
		
		
		clientPlay(s, cs);// already calls es.shutdown(), if that even does anything
		
	}
	
}































