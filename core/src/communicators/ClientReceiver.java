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

public class ClientReceiver implements Runnable {

	private ConnectionSettings cs;
	private GameState gs;
	private DatagramSocket socket;
	
	public ClientReceiver(ConnectionSettings cs, GameState gs) {
		this.cs = cs;
		this.gs = gs;
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
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			byte[] receivedData = new byte[4096]; // TODO main bug will be size of packets
			DatagramPacket packet = new DatagramPacket(receivedData, 4096);
			try {
				socket.receive(packet);
				Input input = new Input(new ByteArrayInputStream(packet.getData()));
				Kryo kryo = new Kryo();
				GameState receivedGs = (GameState)kryo.readClassAndObject(input);
				synchronized (this.gs) {
					this.gs.updateData(receivedGs);
				}
			} catch (IOException e) {
				System.out.println("Error in whileloop in ClientReceiver");
				e.printStackTrace();
				System.exit(1);
			}
					
		}
		
	}
	
}























