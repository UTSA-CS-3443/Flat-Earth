package utilities.ParseMap;


/**
 * map stuff have properties
 * 
 * @author rajkumar
 */
public class Property {
    public String type;
    public String value;

    public Property(String type, String value)
    {
        this.type = type;
        this.value = value;
    }

    public Property(String type)
    {
        this.type = type;
    }
    
    public String toString () {
    	return type + "|" + value;
    }
    
}
