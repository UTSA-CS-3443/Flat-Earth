package controller;

import utilities.KeyboardState;
import utilities.GameState;
import utilities.KeysPressed;
import java.lang.Thread;

/**
 * logic part of the server. Runs concurrently with ServerSender and ServerReceiver. Receives 
 * KeyboardState objects from client, updates the GameState, then sets that game state so the Sender
 * can send it.
 * TODO relationship between this and sender should be that sender waits for a single from this to send
 * 
 * @author mauricio
 *
 */

public class Logic implements Runnable {

	public static int FORCE_CONSTANT = 1;

	// received and updated from clients in ServerReceiver
	private KeyboardState ks;
	// updated and sent to client in Sender
	private GameState gs;
	
	private static final int[][] DIRECTIONS = {{225, 270, 315},{180, -1, 0},{135, 90, 45}};
	private static final int FORCECONSTANT = 50000;
	//private static final int TIME_INTERVAL_MS = 10;
	
	public Logic(KeyboardState ks, GameState gs) {
		this.ks = ks;
		this.gs = gs;
	}
	
	@Override
	public void run() {
		KeysPressed pressed;
		float direction = 0, forceX = 0, forceY = 0;
		boolean movement;
		
		while(!Thread.currentThread().isInterrupted()) {
			// really gotta work on this threading stuff
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pressed = this.ks.get();
			// using boolean arithmetic, this would replace all that shit down there
			forceX = (pressed.left * -FORCE_CONSTANT) + (pressed.right *FORCE_CONSTANT);
			forceY = (pressed.up * FORCE_CONSTANT) + (pressed.down * -FORCE_CONSTANT);
			direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
			movement = !(forceX == 0 && forceY == 0);
			// set the object
			synchronized(this.gs) {
				gs.setCharStats(movement, forceX, forceY, direction);
			}
		}
	}
	
}
















