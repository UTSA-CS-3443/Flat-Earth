package communicators.clientToServer;

public class KeysPressed {
	
	// int's so i can do boolean arithmetic
	public int up = 0;
	public int down = 0;
	public int right = 0;
	public int left = 0;
	
	public boolean defend = false;
	public boolean attack1 = false;
	public boolean attack2 = false;
	
	public int id; // id of the player/client whatever
	
	public String toString() {
		return "" + up + " " + down + " " + right + " " + left;
	}
	
}
