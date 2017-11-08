package utilities;

import utilities.Exit;

/**
 * 
 * Class needs to read our custom file format
 *
 */

public class MapDetails {
	
	public float length;
	public float width;
	public int enemyCount;
	
	public String mapName;
	
	public float enemyX[];
	public float enemyY[];
	
	public MapDetails(String fileName) {
		// TODO open the file. gotta create the file format
		Exit.exit("MapDetails.java: nothings happening in here");
	}
	
}
