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

	// received and udpdated from clients in ServerReciever
	private KeyboardState ks;
	// updated and sent to client in Sender
	private GameState gs;
	// can maybe cut out the need for this. currently maps to the degrees of a circle on a cartesian plane.
	// look at it in 2 dimensions and you'll see it's mapping degrees of rotation (inverted)
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
			// using boolean arithmetic to find the force in the x and y direction. 
			forceX = (pressed.left * -FORCECONSTANT) + (pressed.right *FORCECONSTANT);
			forceY = (pressed.up * FORCECONSTANT) + (pressed.down * -FORCECONSTANT);
			// gets the direction for the animations. i mapped this out on a cartesian plane
			direction = DIRECTIONS[(-pressed.down + pressed.up)+1][(-pressed.left+pressed.right)+1];
			movement = !(forceX == 0 && forceY == 0);
			// set the object 
			synchronized(this.gs) {
				gs.setCharStats(movement, forceX, forceY, direction);
			}
		}
	}
	
}
















