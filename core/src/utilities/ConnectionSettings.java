package utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

// the handshake should be done in javafx, before starting. it should modify this object

public class ConnectionSettings {

	public InetAddress ip;
	
	public int clientReceivePort;
	
		public int serverReceivePort;
	
	
	public ConnectionSettings(String ip, int crp, int srp) {
		this.setIP(ip);
		this.clientReceivePort = crp;
		this.serverReceivePort = srp;
	}
	
	public ConnectionSettings() {
	}
	
	
	
	public void setIP(String ip) {
		try { // this getByName will probably not work when passed a real ip
			this.ip = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			System.out.println("Error getting inetaddress in ConnectionSettings");
			System.exit(1);
		}
	}
	

	
}
