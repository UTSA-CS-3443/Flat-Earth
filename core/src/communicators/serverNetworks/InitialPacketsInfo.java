package communicators.serverNetworks;

import java.util.ArrayList;

import communicators.clientNetworks.InitialClientPacket;

public class InitialPacketsInfo {
	
	public int chosenCharacters[];
	
	public InitialPacketsInfo(int size, ArrayList<InitialClientPacket> packets) {
		chosenCharacters = new int[size];
		for (int i = 0; i < packets.size(); i++)
			chosenCharacters[i] = packets.get(i).chosenCharacter;
	}
	
	public InitialPacketsInfo() { // kryonet needs a no argument constuctor
	}
	
	public InitialPacketsInfo(int chosenChar[]) {
		this.chosenCharacters = chosenChar;
	}
	
}
