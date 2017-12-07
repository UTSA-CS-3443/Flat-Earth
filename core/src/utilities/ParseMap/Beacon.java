package utilities.ParseMap;

/**
 * Spawn beacons
 * 
 * @author rajkumar
 */

public class Beacon extends MapObject{
    private float x;
    private float y;

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public float getX() {
    	return this.x;
    }
    public float getY() {
    	return this.y;
    }
    
    public String toString() {
    	String s = "Beacon: Coordinates: " + this.x + " " + this.y + "------";
    	for (Property p : this.properties)
    		s += p.toString() + "------";
    	s += "Beacon count: " + this.properties.size();
    	return s;
    }
    
}
