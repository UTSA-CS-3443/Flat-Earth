package utilities;

/**
 * some general settings, set by the javafx
 * @author mauricio
 *
 */
public class Settings {
	
	/**
	 * solo, client, or host
	 */
	public int chosenState = 0;
	/**
	 * contrlller or keyboard
	 */
	public boolean controller;
	/**
	 * dpad if controller
	 */
	public boolean dpad;
	/**
	 * music or not
	 */
	public boolean music;
	// for now, 0 for knight, 1 for archer, 2 wizard
	/**
	 * knight, archer, or wizard
	 */
	public int characterType;
	/**
	 * connection stuff
	 */
	public ConnectionSettings connectionSettings;
	
	// received from the server
	/**
	 * player count
	 */
	public int playerCount = 1;
	/**
	 * all character types, received from server
	 */
	public int allCharacterTypes[]; 
	/**
	 * client id
	 */
	public int clientId;
	
	
	
	
}