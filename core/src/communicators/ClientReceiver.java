package communicators;

import java.io.IOException;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import utilities.GameState;
import utilities.ConnectionSettings;

import java.io.ByteArrayInputStream;

/**
 * A thread, instantiated once. Run concurrently with the client. Has access to the same GameState object
 * that the client uses to update it's view. Receives a serialized GameState object from the server, and
 * updates the shared one.
 * 
 * @author mauricio
 *
 */

public class ClientReceiver implements Runnable {

	private ConnectionSettings cs;
	private GameState gs;
	// socket for receiving
	private DatagramSocket socket;
	
	public ClientReceiver(ConnectionSettings cs, GameState gs) {
		this.cs = cs;
		this.gs = gs;
		// start the socket
		try {
			socket = new DatagramSocket(cs.clientReceivePort); // TODO this needs to be done in javafx and passed into here
		} catch (SocketException e) {
			System.out.println("Error creating client receive socket in ClientReceiver");
			e.printStackTrace();
			System.exit(1); // TODO cant keep these, handle errors better
		}
	}
	
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			// TODO sleeping cause these threads are running the cpu to the sky, but this is bad design
			// should think of a better design patterm
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			// Create packet for receiver
			byte[] receivedData = new byte[4096]; // TODO main bug will be size of packets
			DatagramPacket packet = new DatagramPacket(receivedData, 4096);
			//try and receiver
			try {
				socket.receive(packet);
				// packet is received as serialized object (for now). Unserialize it
				Input input = new Input(new ByteArrayInputStream(packet.getData()));
				Kryo kryo = new Kryo();
				// update the shared GameState object
				GameState receivedGs = (GameState)kryo.readClassAndObject(input);
				synchronized (this.gs) {
					//this.gs.updateData(receivedGs); 
					//TODO fix this, this is residual from adding enemies
					// probably just copy over the CharacterState array or something
				}
			} catch (IOException e) {
				System.out.println("Error in whileloop in ClientReceiver");
				e.printStackTrace();
				System.exit(1);
			}
					
		}
		
	}
	
}























