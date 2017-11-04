package controller;

import utilities.KeyboardState;
import utilities.GameState;
import utilities.KeysPressed;
import java.lang.Thread;

import com.badlogic.gdx.graphics.g2d.Sprite;

import controller.entities.*;
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

	ServerEntityManager ntit_man;

	// received and updated from clients in ServerReceiver
	private KeyboardState ks;
	// updated and sent to client in Sender
	private GameState gs;
	
	private static int lastId = 0;
	
	public Logic(KeyboardState ks, GameState gs) {
		this.ks = ks;
		this.gs = gs;
		ntit_man = new ServerEntityManager();
		// this id shit is really hardcoded, and techincally not used. read in entity manager
		ntit_man.addPlayer(new ServerPlayer(0));
		ntit_man.addNpc(new ServerNpc(1));
		//decide amount of enemies and send that to client before this,
		// probably in the javafx initial stuff, but for now just in here
		//TODO HARDCODING THIS RIGHT NOW, HARD CODED FOR THE ONE ENEMY ON THE
		//CLIENT THREAD, DON'T FUCKING FORGET
		//ntit_man.addNpc(new ServerNpc(nextId()));
	}
	
	@Override
	public void run() {
		KeysPressed pressed;
		
		while(!Thread.currentThread().isInterrupted()) {
			// really gotta work on this threading stuff
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pressed = this.ks.get();
			ntit_man.serverUpdateAll(pressed);
			//ServerPlayer c = ntit_man.getPlayer(0);
			
			gs.setStates(ntit_man.getCharacterStates());
			//gs.setCharStats(c.movement, c.forceX, c.forceY, c.direction);

		}
	}
	
	// for now this is done in here, but all this id stuff, assignments, organization.
	// needs to be done in a seperate class, before the logic threads are called, and sent
	// BEFORE the javafx is closed off, so that all clients have the same information. for 
	// the client server architecture to work out properly
	
	
	// right now, the way this works, is the id passed is the index in the arraylist. 
	// TODO this wont work right though cause theres seperate list for players
	// and npcs, and im using this nextid method for both, so the numbers will be 
	// distributed weirdly
	// also, the id's are being set to the player
	private static int nextId() {
		lastId++;
		return lastId - 1;
	}
	
}
















