package communicators.clientNetworks;

public class InitialClientPacket {

	public int chosenCharacter;
	
	public InitialClientPacket(int chosenCharacter) {
		this.chosenCharacter = chosenCharacter;
	}
	
	public InitialClientPacket() {// for kryonet, it needs a zero argument constructor
	}
	
}
