package server;

import java.util.ArrayList;

import communicators.clientToServer.KeyboardState;
import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.GameState;
import utilities.Settings;
import utilities.ParseMap.MapDetails;


/**
 *
 * Main logic thread, holds the game loop. Runs as thread
 * playerCount should be the same as the size of kss. both will be 1 if launching
 * as solo play. this is built to be dynamic to be ran for the server, and also 
 * to be part of the solo play launch
 * @author mauricio
 *
 */
public class Logic implements Runnable {

	/**
	 * game map
	 */
	public static ServerGameMap map;
	
	/**
	 * game state, send to client/kryo server
	 */
	private GameState gs;
	/**
	 * list of keyboard states, per player
	 */
	private ArrayList<KeyboardState> kss;
	
	/**
	 * count of players
	 */
	private int playerCount;
	
	/**
	 * @param settings settings
	 * @param gs gamestate
	 * @param kss keyboard states of players
	 * @param details map details
	 */
	public Logic(Settings settings,GameState gs, ArrayList<KeyboardState> kss, MapDetails details) {
		//Exit.exit("Logic.java: Need to properly set the game state objects for playercount" +
		//			"\n Also need to set the keyboard states right, all before calling this");
		this.gs = gs;
		this.kss = kss;
		this.playerCount = settings.playerCount;
		map = new ServerGameMap(details, playerCount);
		map.initialize(settings);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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














