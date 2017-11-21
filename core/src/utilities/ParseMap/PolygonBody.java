package utilities.ParseMap;

import com.badlogic.gdx.math.Vector2;

import communicators.serverToClient.CharacterState;

import java.util.ArrayList;

/**
 * @author rajkumar
 */

public class PolygonBody extends MapObject{

    private ArrayList<Vector2> vector2s = new ArrayList<Vector2>();

    public void addVector2 (Vector2 vector2)
    {
        this.vector2s.add(vector2);
    }
    public ArrayList<Vector2> getVectors() {
    	return this.vector2s;
    }

    public String toString() {
    	String s = "";
    	for (Vector2 v : vector2s)
    		s += "V: " + v.toString() + " ";
    	return s;
    }
    
    public Vector2[] getArray() {
    	Vector2 vs[] = new Vector2[this.vector2s.size()];
		vs = this.vector2s.toArray(vs);
		return vs;
    }
    
}

