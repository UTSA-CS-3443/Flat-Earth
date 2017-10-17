package communicators;

import utilities.KeyboardState;
import utilities.ConnectionSettings;

import java.io.ByteArrayOutputStream;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;



public class ClientSender implements Runnable {

	private KeyboardState ks;
	private ConnectionSettings cs;
	private DatagramSocket socket;
	
	public ClientSender(ConnectionSettings cs, KeyboardState ks) {
		this.ks = ks;
		this.cs = cs;
		try {
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
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			try {
				output = new Output(new ByteArrayOutputStream() /* should eventually put a buffer size here*/);
				kryo = new Kryo();
				synchronized(ks) { 
					kryo.writeClassAndObject(output, ks); 
				}
				byte[] sendData = output.toBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, cs.ip, cs.serverReceivePort);
				socket.send(sendPacket);
			} catch (Exception e) {
				e.printStackTrace(System.out);
				System.out.println("Something went wrong with kryo or packet  in ClientSender");
				System.exit(1);
			}
		}
	}
	 
	
}










































