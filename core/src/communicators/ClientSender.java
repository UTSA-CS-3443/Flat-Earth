package communicators;

import java.io.ByteArrayOutputStream;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import communicators.clientToServer.KeyboardState;
import utilities.ConnectionSettings;

/**
 * Ran as a thread. Runs concurrently with the client. Has access to the same KeyboardState object shared
 * by client. Sends a copy of that KeyboardState object to the server so it can do the logic
 * @author mauricio
 *
 */

public class ClientSender implements Runnable {

	private KeyboardState ks;
	private ConnectionSettings cs;
	// socket for connection
	private DatagramSocket socket;
	
	public ClientSender(ConnectionSettings cs, KeyboardState ks) {
		this.ks = ks;
		this.cs = cs;
		try { // try and create the socket
			socket = new DatagramSocket();
		} catch (SocketException e1) { // TODO this needs to be passed in by javafx so it can handle these exceptions
			System.out.println("Error creating socket in CleintSender");
			e1.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		Output output = null;
		Kryo kryo;
		while (!Thread.currentThread().isInterrupted()) {
			try { // TODO sleeping in threads is bad practice, but for now this will chill the cpu
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			try { // create the kryo object for serializing the keyboardstate object
				output = new Output(new ByteArrayOutputStream() /* should eventually put a buffer size here*/);
				kryo = new Kryo();
				synchronized(ks) { 
					kryo.writeClassAndObject(output, ks); 
				}
				// create send packet
				byte[] sendData = output.toBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, cs.ip, cs.serverReceivePort);
				// send
				socket.send(sendPacket);
			} catch (Exception e) {
				e.printStackTrace(System.out);
				System.out.println("Something went wrong with kryo or packet  in ClientSender");
				System.exit(1);
			}
		}
	}
	 
	
}










































