package server;

import java.util.ArrayList;

import communicators.clientToServer.KeyboardState;
import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.GameState;
import utilities.Settings;
import utilities.ParseMap.MapDetails;


/**
 * 	playerCount should be the same as the size of kss. both will be 1 if launching
 *	as solo play. this is built to be dynamic to be ran for the server, and also 
 *	to be part of the solo play launch
 * @author mauricio
 *
 *	should be used like this:
 *		MapDetails details = new MapDetails(mapName);
 *		Logic logic = new Logic(count, gs, kss);
 *		logic.setMapLogistics(details)
 *
 */

public class Logic implements Runnable {

	public static ServerGameMap map;
	
	private GameState gs;
	private ArrayList<KeyboardState> kss;
	
	private int playerCount;
	
	public Logic(Settings settings,GameState gs, ArrayList<KeyboardState> kss, MapDetails details) {
		//Exit.exit("Logic.java: Need to properly set the game state objects for playercount" +
		//			"\n Also need to set the keyboard states right, all before calling this");
		this.gs = gs;
		this.kss = kss;
		this.playerCount = settings.playerCount;
		map = new ServerGameMap(details, playerCount);
		map.initialize(settings);
	}
	
	
	@Override
	public void run() {
		
		KeysPressed pressed[] = new KeysPressed[this.playerCount];
		float currentTime = System.nanoTime(), lastTime = System.nanoTime();
		
		while(!Thread.currentThread().isInterrupted()) {
			//Sys.sleep(1, "Logic.java: sleep error");
			// get keys pressed TODO maybe lock the objects, otherwise this will 
			// be inefficient cause of having to wait for all of the keyboard states. 
			// either that or make a wrapper class that holds the array and lock that
			// object while getting
			
			for (int i = 0; i < this.kss.size(); i++) {
				pressed[i] = this.kss.get(i).getKeysPressed();
				//Sys.print(pressed[i].toString() + " at " + i);
			}
			lastTime = currentTime;
			currentTime = System.nanoTime();
			//Sys.print("" + (currentTime - lastTime));
			//Sys.print("" + currentTime + " -- " + lastTime);
			//update everything
			map.update(pressed, (currentTime - lastTime) / 1000000); // delta time for now
			
			//wrtie to gamestate
			this.gs.setStates(map.getEntityManager().getCharacterStates());
			this.gs.setSkillStates(map.getEntityManager().getSkillStates());
		}
	}
	
}














