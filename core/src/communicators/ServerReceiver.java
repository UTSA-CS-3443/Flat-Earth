package communicators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import utilities.ConnectionSettings;
import utilities.KeyboardState;

public class ServerReceiver implements Runnable {

	private KeyboardState ks;
	private ConnectionSettings cs;
	private DatagramSocket socket;
	
	public ServerReceiver(KeyboardState ks, ConnectionSettings cs) {
		this.ks = ks;
		this.cs = cs;
		try {
			socket = new DatagramSocket(cs.serverReceivePort); // TODO this needs to be done in javafx and passed into here
		} catch (SocketException e) {
			System.out.println("Error creating client receive socket in ClientReceiver");
			e.printStackTrace();
			System.exit(1); // TODO cant keep these, handle errors better
		}
	}
	
	@Override // TODO check
	public void run() {
		while(!Thread.currentThread().isInterrupted()) { // not even sure if this does what i want it to
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
				KeyboardState receivedKs = (KeyboardState)kryo.readClassAndObject(input);
				synchronized (this.ks) {
					this.ks.updateData(receivedKs);
				}
			} catch (IOException e) {
				System.out.println("Error in whileloop in ServerReceiver");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}











