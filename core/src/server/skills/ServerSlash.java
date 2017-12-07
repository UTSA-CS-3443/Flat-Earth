package server.skills;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.SkillState;
import server.ServerGameMap;
import server.entities.PosAndDir;
import server.entities.ServerCharacter;
import server.entities.ServerSpawner;

/**
 * slash for the knight, handles the construction
 * 
 * @author mauricio
 *
 */
public class ServerSlash extends PerformableSkill {
	
	/**
	 * for collisions
	 */
	private Body body;
    /**
     * size
     */
    private float size;
	/**
	 * map its on, for removing itself
	 */
	private ServerGameMap gameMap;
	/**
	 * x inti
	 */
	private float xInitial;
	/**y inti
	 * 
	 */
	private float yInitial;
	/**
	 * for physics
	 */
	private short category = ServerSpawner.ATTACK_PHYSICS;
	/**
	 * for physics
	 */
	private short mask = ServerSpawner.FOOT_PHYSICS;
	/**
	 * who casted
	 */
	private PosAndDir caster;
	/**
	 * alive tiem
	 */
	private float timeAlive = 0;
	/**
	 * alotted time
	 */
	private float maxTimeAlive = .7f;
		
	/**
	 * @param caster who casted
	 * @param gameMap map its on
	 */
	public ServerSlash(PosAndDir caster, ServerGameMap gameMap) {
		super(caster, 5);
        this.caster = caster;
        this.size = .5f;
        this.gameMap = gameMap;
        this.xInitial = this.position.x;
        this.yInitial = this.position.y;
        this.category = ServerSpawner.ATTACK_PHYSICS;
        this.mask = ServerSpawner.BOUNDING_BODY_PHYSICS | ServerSpawner.FOOT_PHYSICS;
        this.power = 6;
	}

	/* (non-Javadoc)
	 * @see server.skills.ServerSkill#perform(server.entities.PosAndDir)
	 */
	@Override
	public void perform(PosAndDir initial) {
		this.gameMap.getEntityManager().updateables.add(this);
        this.gameMap.getEntityManager().activeSkills.add(this);
        this.body = ServerSpawner.createWeaponBody(this.gameMap.getWorld(), this.xInitial, this.yInitial, this.category, this.mask, this.size, new Vector2(-this.size / 2, -this.size),
                new Vector2(this.size / 2, -this.size),
                new Vector2(this.size, -this.size / 2),
                new Vector2(this.size, this.size / 2),
                new Vector2(this.size / 2, this.size),
                new Vector2(-this.size / 2, this.size),
                new Vector2(-this.size, this.size / 2),
                new Vector2(-this.size, -this.size / 2));
        this.body.setUserData(this);
	}

	/* (non-Javadoc)
	 * @see server.Updateable#update(float)
	 */
	@Override
	public void update(float delta) {
		if (timeAlive > maxTimeAlive) {
			this.gameMap.getEntityManager().updateables.removeValue(this, false);
    	    this.gameMap.getEntityManager().activeSkills.removeValue(this, false);
    	    gameMap.getEntityManager().bodiesToDestroy.add(this.body);
    	    return;
		}
		timeAlive += delta;
	}
	
	/* (non-Javadoc)
	 * @see server.skills.ServerSkill#copy()
	 */
	@Override
	public ServerSkill copy() {
		return new ServerSlash(this.caster, this.gameMap);
	}

	/* (non-Javadoc)
	 * @see server.skills.ServerSkill#getState()
	 */
	@Override
	public SkillState getState() {
		return new SkillState(this.position.x, this.position.y, this.direction, SkillType.SLASH);
	}
	
	/* (non-Javadoc)
	 * @see server.skills.ServerSkill#setPerformer(server.entities.ServerCharacter)
	 */
	@Override 
	public void setPerformer(ServerCharacter c) {
		this.performer = c;
	}
	
}
