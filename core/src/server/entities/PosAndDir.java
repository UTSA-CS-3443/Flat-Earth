package server.entities;

import com.badlogic.gdx.math.Vector2;
/**
 * anything that has a position and direction, super class
 * @author mauricio
 *
 */
public interface PosAndDir
{
    Vector2 getPosition();
    float getDirection();
}
