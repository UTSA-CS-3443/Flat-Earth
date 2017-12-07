package utilities;

/**
 * Connection settings. A global one should be modified and created in the javafx, which should
 * create the handshake with tcp BEFORE the game starts
 * @author mauricio
 *
 */

public class ConnectionSettings {
	
//	// IP object needed by java's udp connections
//	public InetAddress ip;
//	
//	// port for client to receive from
//	public int clientReceivePort;
//	
//	// port for server to recieve on
//	public int serverReceivePort;
//	
//	public ConnectionSettings(String ip, int crp, int srp) {
//		this.setIP(ip);
//		this.clientReceivePort = crp;
//		this.serverReceivePort = srp;
//	}
//	
//	public ConnectionSettings() {
//	}
//	
//	public void setIP(String ip) {
//        try { // this getByName will probably not work when passed a real ip
//            this.ip = InetAddress.getByName(ip);
//        } catch (UnknownHostException e) {
//            System.out.println("Error getting inetaddress in ConnectionSettings");
//            System.exit(1);
//        }
//    }
	
	
	public int serverTcpPort = 8080;
	public int serverUdpPort = 1099;

}
