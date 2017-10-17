package controller;

import utilities.KeyboardState;
import utilities.GameState;
import java.lang.Thread;

public class Logic implements Runnable {

	private KeyboardState ks;
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
			//for (boolean b : pressed)
			//	System.out.println(b);
			// OPTIMIZE there's gotta be a cleaner way to do this
			forceX = forceY = direction = 0;
			// basically strict copy and paste from ash's code. no keys.UP though
			if(pressed[0])
			{
				forceY = 50000;
				direction = 90;
				if(pressed[2])
					direction = 45;
				else if(pressed[3])
					direction = 135;
			}
			else if(pressed[1])
			{
				forceY = -50000;
				direction = 270;
				if(pressed[2])
					direction = 225;
				else if(pressed[3])
					direction = 315;
			}
			if(pressed[2])
			{
				forceX = -50000;
				direction = 180;
				if(pressed[0])
					direction = 135;
				else if(pressed[1])
					direction = 225;
			}
			else if(pressed[3])
			{
				forceX = 50000;
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
			synchronized(this.gs) {
				gs.setCharStats(movement, forceX, forceY, direction);
			}
		}
	}

	
}
