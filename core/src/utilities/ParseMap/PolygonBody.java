package utilities.ParseMap;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * @author rajkumar
 */

public class PolygonBody extends MapObject{

    private ArrayList<Vector2> vector2s = new ArrayList<>();

    public void addVector2 (Vector2 vector2)
    {
        this.vector2s.add(vector2);
    }

}

