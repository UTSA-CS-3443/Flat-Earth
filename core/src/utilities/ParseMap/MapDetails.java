//package utilities.ParseMap;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//
///**
// * Parses the map text file that was created by the map maker
// * @author rajkumar
// */
//
//public class MapDetails {
//	// what is this
//	private boolean isPolygon;
//	private boolean isBeacon;
//	/****/
//	private Sprite mapSprite; // it'd be nice to do all io in here
//
//	public String mapName;
//	private ArrayList<PolygonBody> polygonBodies = new ArrayList<PolygonBody>();
//	private ArrayList<Beacon> beacons = new ArrayList<Beacon>();
//
//	private PolygonBody polygonBody;
//	private Beacon beacon;
//
//	private Vector2 vector;
//	private Property property;
//
//	public MapDetails(String fileName) throws IOException, ParseException {
//		//this.mapSprite = new Sprite(new Texture(fileName+".png"));
//		String line;
//		File fl = new File(fileName);
//		FileReader frd = new FileReader(fl);
//		BufferedReader brd = new BufferedReader(frd);
//		line = brd.readLine();
//		this.mapName = line;
//
//		while ((line = brd.readLine()) != null) {
//			if (line.equals("<polygon>")) {
//				this.isPolygon = true;
//				this.isBeacon = false;
//				this.polygonBody = new PolygonBody();
//				continue;
//			} else if (line.equals("</polygon>")) {
//				this.polygonBodies.add(this.polygonBody);
//				this.isPolygon = false;
//				continue;
//			} else if (line.equals("<beacon>")) {
//				this.isBeacon = true;
//				this.isPolygon = false;
//				this.beacon = new Beacon();
//				continue;
//			} else if (line.equals("</beacon>")) {
//				this.isBeacon = false;
//				this.beacons.add(this.beacon);
//				continue;
//			}
//
//			if (this.isPolygon)
//				createPolygonProperties(line);
//			else if (this.isBeacon)
//				createBeaconProperties(line);
//		}
//		brd.close();
//		frd.close();
//	}
//
//	public Sprite getMapSprite() {
//		return mapSprite;
//	}
//
//	public String getMapName() {
//		return mapName;
//	}
//
//	public ArrayList<PolygonBody> getPolygonBodies() {
//		return polygonBodies;
//	}
//
//	public ArrayList<Beacon> getBeacons() {
//		return beacons;
//	}
//
//	// where my other properties at raj
//	public final void createPolygonProperties(String line) {
//		String[] words = line.split(" ");
//
//		if (words[0].equals("coordinates")) {
//			for (int i = 1; i < words.length; i++) {
//				if (i % 2 != 0) {
//					this.vector = new Vector2();
//					this.vector.x = Float.parseFloat(words[i]);
//				} else {
//					this.vector.y = Float.parseFloat(words[i]);
//					this.polygonBody.addVector2(vector);
//				}
//			}
//		} else {
//			if (words.length > 1)
//				this.property = new Property(words[0], words[1]);
//			else
//				this.property = new Property(words[0]);
//			this.polygonBody.addProperty(this.property);
//		}
//	}
//
//	public final void createBeaconProperties(String line) {
//		String[] words = line.split(" ");
//
//		if (words[0].equals("coordinates")) {
//			this.vector = new Vector2();
//			this.beacon.setPosition(Float.parseFloat(words[1]), Float.parseFloat(words[2]));
//			this.beacon.addProperty(this.property);
//		} else {
//			if (words.length > 1)
//				this.property = new Property(words[0], words[1]);
//			else
//				this.property = new Property(words[0]);
//			this.beacon.addProperty(this.property);
//		}
//	}
//}
//
//package utilities.ParseMap;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//import utilities.Exit;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//
///**
// * Parses the map text file that was created by the map maker
// * @author rajkumar
// */
//
//public class MapDetails {
//	// what is this
//	private boolean isPolygon;
//	private boolean isBeacon;
//	/****/
//	private Sprite mapSprite; // it'd be nice to do all io in here
//
//	/****/
//	public String mapName;
//
//	private ArrayList<PolygonBody> polygonBodies = new ArrayList<PolygonBody>();
//	private ArrayList<Beacon> beacons = new ArrayList<Beacon>();
//
//	private PolygonBody polygonBody;
//	private Beacon beacon;
//
//	private Vector2 vector;
//	private Property property;
//
//	public MapDetails(String fileName) throws IOException, ParseException {
//		//this.mapSprite = new Sprite(new Texture(mapName+".png"));
//		String line;
//		File fl = new File(fileName);
//		FileReader frd = new FileReader(fl);
//		BufferedReader brd = new BufferedReader(frd);
//		line = brd.readLine();
//		this.mapName = line;
//
//		while ((line = brd.readLine()) != null) {
//
//			if (line.equals("<polygon>")) {
//				this.isPolygon = true;
//				this.isBeacon = false;
//				this.polygonBody = new PolygonBody();
//				continue;
//			} else if (line.equals("</polygon>")) {
//				this.polygonBodies.add(this.polygonBody);
//				this.isPolygon = false;
//				continue;
//			} else if (line.equals("<beacon>")) {
//				this.isBeacon = true;
//				this.isPolygon = false;
//				this.beacon = new Beacon();
//				continue;
//			} else if (line.equals("</beacon>")) {
//				this.isBeacon = false;
//				this.beacons.add(this.beacon);
//				continue;
//			}
//
//			if (this.isPolygon)
//				createPolygonProperties(line);
//			else if (this.isBeacon)
//				createBeaconProperties(line);
//		}
//		brd.close();
//		frd.close();
//	}
//	
//
//	public final void createPolygonProperties(String line) {
//		String[] words = line.split(" ");
//		String property = "";
//		String value = "";
//
//		boolean begin = true;
//		boolean end = true;
//		int j = 0;
//
//		if (words[0].equals("coordinates"))
//		{
//			for (int i = 1; i < words.length; i++)
//			{
//				if (i % 2 != 0)
//				{
//					this.vector = new Vector2();
//					this.vector.x = Float.parseFloat(words[i]);
//				}
//				else
//				{
//					this.vector.y = Float.parseFloat(words[i]);
//					this.polygonBody.addVector2(vector);
//				}
//			}
//		}
//		else
//		{
//			for (int i = 0; i < line.length(); i++)
//			{
//				if (end && line.charAt(i) == '"')
//				{
//					begin = true;
//					end = false;
//					continue;
//				}
//
//				if (begin && line.charAt(i) == '"')
//				{
//					begin = false;
//					end = true;
//					continue;
//				}
//
//				if (end && line.charAt(i) == ' ')
//				{
//					j++;
//					continue;
//				}
//
//				if (j == 0)
//					property = property + line.charAt(i);
//				else
//					value = value + line.charAt(i);
//			}
//
//			if (value.length() > 1)
//				this.property = new Property(property, value);
//			else
//				this.property = new Property(property);
//
//			this.polygonBody.addProperty(this.property);
//		}
//	}
//
//	public final void createBeaconProperties(String line)
//	{
//		String[] words = line.split(" ");
//		String property = "";
//		String value = "";
//
//		boolean begin = true;
//		boolean end = true;
//		int j = 0;
//
//		if (words[0].equals("coordinates"))
//		{
//			this.vector = new Vector2();
//			try {
//				this.beacon.setPosition(Float.parseFloat(words[1]), Float.parseFloat(words[2]));
//			} catch(Exception e) {
//				e.printStackTrace();
//				System.out.printf("%s %s\n", words[1], words[2]);
//				Exit.exit("MapDetails.java: probably a number format excption");
//			}
//			this.beacon.addProperty(this.property);
//		}
//		else
//		{
//			for (int i = 0; i < line.length(); i++)
//			{
//				if (end && line.charAt(i) == '"')
//				{
//					begin = true;
//					end = false;
//					continue;
//				}
//
//				if (begin && line.charAt(i) == '"')
//				{
//					begin = false;
//					end = true;
//					continue;
//				}
//
//				if (end && line.charAt(i) == ' ')
//				{
//					j++;
//					continue;
//				}
//
//				if (j == 0)
//					property = property + line.charAt(i);
//				else
//					value = value + line.charAt(i);
//			}
//
//			if (value.length() > 1)
//				this.property = new Property(property, value);
//			else
//				this.property = new Property(property);
//
//			this.polygonBody.addProperty(this.property);
//		}
//	}
//	
//	
//	
package utilities.ParseMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import utilities.Sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Parses the map text file that was created by the map maker
 * @author rajkumar
 */

public class MapDetails {
	// this scale is used for all sprites and all values read in from the map parser
	// not called in this class, used outside it.
	public static final float SCALE = (float) (1.0/16.0);
	
	// what is this
	private boolean isPolygon;
	private boolean isBeacon;
	/****/
	private Sprite mapSprite; // it'd be nice to do all io in here

	/****/
	public String mapName;

	private ArrayList<PolygonBody> polygonBodies = new ArrayList<PolygonBody>();
	private ArrayList<Beacon> beacons = new ArrayList<Beacon>();

	private PolygonBody polygonBody;
	private Beacon beacon;

	private Vector2 vector;
	private Property property;

	public MapDetails(String fileName) throws IOException, ParseException {
		//this.mapSprite = new Sprite(new Texture(mapName+".png"));
		String line;
		File fl = new File(fileName);
		FileReader frd = new FileReader(fl);
		BufferedReader brd = new BufferedReader(frd);
		line = brd.readLine();
		this.mapName = line;

		while ((line = brd.readLine()) != null) {
			if (line.equals("<polygon>")) {
				this.isPolygon = true;
				this.isBeacon = false;
				this.polygonBody = new PolygonBody();
				continue;
			} else if (line.equals("</polygon>")) {
				this.polygonBodies.add(this.polygonBody);
				this.isPolygon = false;
				continue;
			} else if (line.equals("<beacon>")) {
				this.isBeacon = true;
				this.isPolygon = false;
				this.beacon = new Beacon();
				continue;
			} else if (line.equals("</beacon>")) {
				this.isBeacon = false;
				this.beacons.add(this.beacon);
				continue;
			}

			if (this.isPolygon)
				createPolygonProperties(line);
			else if (this.isBeacon)
				createBeaconProperties(line);
		}
		brd.close();
		frd.close();
	}

	public final void createPolygonProperties(String line) {
		String[] words = line.split(" ");

		if (words[0].equals("coordinates")) {
			for (int i = 1; i < words.length; i++) {
				if (i % 2 != 0) {
					this.vector = new Vector2();
					this.vector.x = Float.parseFloat(words[i]);
				} else {
					this.vector.y = Float.parseFloat(words[i]);
					this.polygonBody.addVector2(vector);
				}
			}
		} else {
			if (words.length > 1)
				this.property = new Property(words[0], words[1]);
			else
				this.property = new Property(words[0]);
			this.polygonBody.addProperty(this.property);
		}
	}

	public final void createBeaconProperties(String line) {
		String[] words = line.split(" ");

		if (words[0].equals("coordinates")) {
			this.vector = new Vector2();
			this.beacon.setPosition(Float.parseFloat(words[1]), Float.parseFloat(words[2]));
			this.beacon.addProperty(this.property);
		} else {
			if (words.length > 1)
				this.property = new Property(words[0], words[1]);
			else
				this.property = new Property(words[0]);
			this.beacon.addProperty(this.property);
		}
		//Sys.print(this.property.toString());
	}


	public Sprite getMapSprite() {
		return mapSprite;
	}

	public String getMapName() {
		return mapName;
	}

	public ArrayList<PolygonBody> getPolygonBodies() {
		return polygonBodies;
	}

	public ArrayList<Beacon> getBeacons() {
		return beacons;
	}
	
}







