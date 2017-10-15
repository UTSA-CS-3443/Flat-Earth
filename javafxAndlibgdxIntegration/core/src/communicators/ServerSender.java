package communicators;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import utilities.ConnectionSettings;
import utilities.GameState;

public class ServerSender implements Runnable {

	private GameState gs;
	private ConnectionSettings cs;
	private DatagramSocket socket;
	
	public ServerSender(GameState gs, ConnectionSettings cs) {
		this.gs = gs;
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
		Kryo kryo = null;
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			try { 
				output = new Output(new ByteArrayOutputStream() /* should eventually put a buffer size here*/);
				kryo = new Kryo();
				synchronized(gs) { 
					kryo.writeClassAndObject(output, gs); 
				}
				byte[] sendData = output.toBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, cs.ip, cs.clientReceivePort);
				socket.send(sendPacket);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Something went wrong with kryo or packet  in ServerSender");
				System.exit(1);
			}
		}
	}

}
