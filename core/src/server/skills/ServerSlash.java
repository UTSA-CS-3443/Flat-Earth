package server.skills;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.SkillState;
import server.ServerGameMap;
import server.entities.PosAndDir;
import server.entities.ServerSpawner;

public class ServerSlash extends PerformableSkill {
	
	private Body body;
    private float size;
	private ServerGameMap gameMap;
	private float xInitial;
	private float yInitial;
	private short category = ServerSpawner.ATTACK_PHYSICS;
	private short mask = ServerSpawner.FOOT_PHYSICS;
	private PosAndDir caster;
	private float timeAlive = 0;
	private float maxTimeAlive = .7f;
	
	public ServerSlash(PosAndDir caster, ServerGameMap gameMap) {
		super(caster, 5);
        this.caster = caster;
        this.size = .5f;
        this.gameMap = gameMap;
        this.xInitial = this.position.x;
        this.yInitial = this.position.y;
        this.category = ServerSpawner.ATTACK_PHYSICS;
        this.mask = ServerSpawner.BOUNDING_BODY_PHYSICS | ServerSpawner.FOOT_PHYSICS;
	}

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
	
	@Override
	public ServerSkill copy() {
		return new ServerSlash(this.caster, this.gameMap);
	}

	@Override
	public SkillState getState() {
		return new SkillState(this.position.x, this.position.y, this.direction, SkillType.SLASH);
	}
	
}
