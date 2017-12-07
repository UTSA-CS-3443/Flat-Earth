package utilities.ParseMap;

import java.util.ArrayList;

/**
 * Beacons and polygons have properties
 * 
 * @author rajkumar
 */

public abstract class MapObject
{
    protected ArrayList<Property> properties = new ArrayList<Property>();

    public final void addProperty (Property property)
    {
        this.properties.add(property);
    }

    public ArrayList<Property> getProperties() {
    	return this.properties;
    }
    
}
