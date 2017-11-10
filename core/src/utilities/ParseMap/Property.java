package utilities.ParseMap;


/**
 * @author rajkumar
 */
public class Property {
    private String type;
    private String value;

    public Property(String type, String value)
    {
        this.type = type;
        this.value = value;
    }

    public Property(String type)
    {
        this.type = type;
    }
}
