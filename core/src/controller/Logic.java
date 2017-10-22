package controller;

import utilities.KeyboardState;
import utilities.GameState;
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

	public static final int FORCE_CONSTANT = 1500;

	// received and updated from clients in ServerReceiver
	private KeyboardState ks;
	// updated and sent to client in Sender
	private GameState gs;
	
	//private static final int TIME_INTERVAL_MS = 10;
	
	public Logic(KeyboardState ks, GameState gs) {
		this.ks = ks;
		this.gs = gs;
	}
	
	@Override
	public void run() {
		boolean [] pressed;
		float direction = 0, forceX = 0, forceY = 0;
		boolean movement;
		
		// some timestamping stuff recommended by Mark Robinson
		// he's saying that synchronized methods have a solid amount of over hang to them. so
		// it's better to slow down your process
		//long currentTime = System.currentTimeMillis();
		//long lastCallTime = 0;
		
		while(!Thread.currentThread().isInterrupted()) {
			// time stamp stuff
			/*currentTime = System.currentTimeMillis();
			if (currentTime -lastCallTime < TIME_INTERVAL_MS) {
				continue;
			}
			lastCallTime = currentTime;*/
			// really gotta work on this threading stuff
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// this synchronized method call is expensive as Mark Robins said (cause synchronized
			// has a lot of over hang
			pressed = this.ks.get();

			if(pressed[0])
			{
				forceY = FORCE_CONSTANT;
				direction = 90;
				if(pressed[2])
					direction = 45;
				else if(pressed[3])
					direction = 135;
			}
			else if(pressed[1])
			{
				forceY = -FORCE_CONSTANT;
				direction = 270;
				if(pressed[2])
					direction = 225;
				else if(pressed[3])
					direction = 315;
			}
			if(pressed[2])
			{
				forceX = -FORCE_CONSTANT;
				direction = 180;
				if(pressed[0])
					direction = 135;
				else if(pressed[1])
					direction = 225;
			}
			else if(pressed[3])
			{
				forceX = FORCE_CONSTANT;
				direction = 0;
				if(pressed[0])
					direction = 45;
				else if(pressed[1])
					direction = 315;
			}
			if (forceX == 0 && forceY == 0) {
				movement = false;
			} else {
				movement = true;
			}
			// set the object
			synchronized(this.gs) {
				gs.setCharStats(movement, forceX, forceY, direction);
			}
		}
	}

	
}
