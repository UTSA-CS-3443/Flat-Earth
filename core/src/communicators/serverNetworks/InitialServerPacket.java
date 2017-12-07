package communicators.serverNetworks;

public class InitialServerPacket {

	public int id;
	public InitialPacketsInfo otherClientsInfo;
	
	public InitialServerPacket(int id, InitialPacketsInfo packetsInfo) {
		this.otherClientsInfo = packetsInfo;
		this.id = id;
	}

	public InitialServerPacket() {// for kryonet, it needs a zero argument constructor
	}
	
}
