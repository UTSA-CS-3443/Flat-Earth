package utilities.ParseMap;

import java.util.ArrayList;

/**
 * @author rajkumar
 */

public abstract class MapObject
{
    protected ArrayList<Property> properties = new ArrayList<Property>();

    public final void addProperty (Property property)
    {
        this.properties.add(property);
    }

}
