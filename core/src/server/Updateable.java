package server;

/**
 * things that are updateable
 * 
 * @author mauricio
 *
 */
public interface Updateable
{
    /**
     * @param delta time
     */
    void update(float delta);
}
