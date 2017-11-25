package server.skills;

import com.badlogic.gdx.math.Vector2;
import server.Updateable;
import server.entities.PosAndDir;

public abstract class PerformableSkill implements ServerSkill, PosAndDir, Updateable
{
    protected float power;
    protected Vector2 position;
    protected float direction;

    public PerformableSkill(PosAndDir caster, float power)
    {
        this.position = new Vector2(caster.getPosition());
        this.direction = caster.getDirection();
        this.power = power;
    }

    @Override
    public float getPower()
    {
        return this.power;
    }

    @Override
    public Vector2 getPosition() { return this.position; }

    @Override
    public float getDirection() { return 0; }
}
