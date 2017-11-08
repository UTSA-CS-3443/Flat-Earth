package server;

import java.util.ArrayList;

import communicators.clientToServer.KeyboardState;
import communicators.clientToServer.KeysPressed;
import communicators.serverToClient.GameState;
import utilities.Exit;
import utilities.MapDetails;
/**
 * 	playerCount should be the same as the size of kss. both will be 1 if launching
 *	as solo play. this is built to be dynamic to be ran for the server, and also 
 *	to be part of the solo play launch
 * @author mauricio
 *
 */

public class Logic implements Runnable {

	ServerGameMap map;
	
	private GameState gs;
	private ArrayList<KeyboardState> kss;
	
	private int playerCount;
	
	private boolean mapLogisticsSet = false;

	public Logic(int playerCount, GameState gs, ArrayList<KeyboardState> kss) {
		Exit.exit("Logic.java: Need to properly set the game state objects for playercount" +
					"\n Also need to set the keyboard states right, all before calling this");
		this.gs = gs;
		this.kss = kss;
		this.playerCount = playerCount;
	

	}
	
	public void setMapLogistics(String mapName) {
		map = new ServerGameMap(new MapDetails(mapName), playerCount);
		map.initialize();
		this.mapLogisticsSet = true;
	}
	
	@Override
	public void run() {
		
		if(!mapLogisticsSet)
			Exit.exit("Logic.java: didn't call setMapLogistics()");
		
		KeysPressed pressed[] = new KeysPressed[this.playerCount];
		
		while(!Thread.currentThread().isInterrupted()) {
			
			// get keys pressed
			for (int i = 0; i < this.kss.size(); i++)
				pressed[i] = this.kss.get(i).getKeysPressed();
			
			Exit.exit("Actually caluculate this");
			float deltaTime = 0;//current time - last time
			// last time = current time
			
			//update everything
			map.update(pressed, deltaTime);
			
			//wrtie to gamestate
			this.gs.setStates(map.getEntityManager().getStates());
		}
	}
	
}














