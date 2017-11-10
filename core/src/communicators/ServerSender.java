package communicators;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import communicators.serverToClient.GameState;
import utilities.ConnectionSettings;

/**
 * A thread. Runs concurrently with server. Shares the GameSate object with it. Send that object to the
 * clients (not plural for now). Should probably instantiate this as now a while loop, only when they need
 * to be sent
 * @author mauricio
 *
 */

public class ServerSender implements Runnable {

	private GameState gs;
	private ConnectionSettings cs;
	// socket for connection
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
			// TODO sleeps are bad in threads but this chills the cpu for now
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("sleep error");
			}
			try {
				// serielaize the object
				output = new Output(new ByteArrayOutputStream() /* should eventually put a buffer size here*/);
				kryo = new Kryo();
				synchronized(gs) { 
					kryo.writeClassAndObject(output, gs); 
				}
				// send packet
				byte[] sendData = output.toBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, cs.ip, cs.clientReceivePort);
				// send
				socket.send(sendPacket);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Something went wrong with kryo or packet  in ServerSender");
				System.exit(1);
			}
		}
	}

}
