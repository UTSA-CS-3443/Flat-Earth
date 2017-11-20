package utilities;

/**
 * some general settings, set by the javafx
 * @author mauricio
 *
 */

public class Settings {
	
	public int chosenState = 0;
	public boolean controller;
	public boolean dpad;
	public boolean music;
	// for now, 0 for knight, 1 for archer, 2 wizard
	public int characterType;
	public ConnectionSettings connectionSettings;
	
	// received from the server
	public int playerCount = 1;
	public int allCharacterTypes; 
	public int clientId;
	
}