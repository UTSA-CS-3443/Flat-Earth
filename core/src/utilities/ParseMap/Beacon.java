package utilities.ParseMap;

import java.util.ArrayList;

/**
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
}
