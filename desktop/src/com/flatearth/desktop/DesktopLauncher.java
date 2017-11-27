package com.flatearth.desktop;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import communicators.clientToServer.KeyboardState;
import communicators.serverToClient.GameState;
import server.Logic;
import startGui.Launcher;
import utilities.*;
import utilities.ParseMap.MapDetails;

public class DesktopLauncher {
	
	//thread pool for launching the threads
	private static ExecutorService es; 
	// general settings, will be set by javafx
	public static Settings s;
	
	public static void main (String[] args) {
		printMessages();
		// create thread pool
		es = Executors.newCachedThreadPool();
		s = new Settings();
		// TODO this should be set in the javafx, but for now it's just hardcoded
		s.connectionSettings = new ConnectionSettings("localhost", 9876, 9877);
		// launch the javafx
		// sets settings object, also creates handshake for connection settings
		Launcher.launchGui(args);
		//s.characterType = 1;
		switch(s.chosenState) {
		case 0: // currently runs even if they close the window instead of hitting start. fix that
			soloPlay();
			break;
		case 1:
			Sys.exit("DekstopLauncher: nada va pasar ");
		//	clientPlay(s);
			break;
		case 2: 
			Sys.exit("DekstopLauncher: nada va pasar ");
		//	hostPlay(s);
			break;
		}
	}
	
	public static void soloPlay() {
		MapDetails details = null;
		try {
			details = new MapDetails("map_polygons.pp");
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
		// SETTING FULLSCREEN REALLY MESSES WITH MY UBUNTU 16.04, probably don't do it
		new LwjglApplication(new Game(s, gs, kss.get(0), details), config);
	}
	
	
	//TODO all of these
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































