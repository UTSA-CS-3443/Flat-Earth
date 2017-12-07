package server.skills;

import com.badlogic.gdx.math.Vector2;
import server.Updateable;
import server.entities.PosAndDir;

/**
 * skills that can be performed on screen
 * 
 * @author ash
 *
 */
public abstract class PerformableSkill extends ServerSkill implements PosAndDir, Updateable
{
    /**
     * damage 
     */
    protected float power;
    /**
     * position
     */
    protected Vector2 position;
    /**
     * direction
     */
    protected float direction;
    
    /**
     * @param caster who casted
     * @param power damager
     */
    public PerformableSkill(PosAndDir caster, float power)
    {
        this.position = new Vector2(caster.getPosition());
        this.direction = caster.getDirection();
        this.power = power;
    }

    /* (non-Javadoc)
     * @see server.skills.ServerSkill#getPower()
     */
    @Override
    public float getPower()
    {
        return this.power;
    }

    /* (non-Javadoc)
     * @see server.entities.PosAndDir#getPosition()
     */
    @Override
    public Vector2 getPosition() { return this.position; }

    /* (non-Javadoc)
     * @see server.entities.PosAndDir#getDirection()
     */
    @Override
    public float getDirection() { return 0; }

    
}
