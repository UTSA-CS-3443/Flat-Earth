package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.ActionTrigger;
import communicators.serverToClient.CharacterState;
import server.ServerGameMap;
import server.skills.ServerSkill;

/**
 * Npc "AI" class
 * 
 * @author mauricio
 *
 */
public abstract class ServerNpc extends ServerCharacter {

	
	/**
	 * last time attacked
	 */
	private float lastAttack = 0;
	/**
	 * distance
	 */
	protected float attackDistance;
	
	/**
	 * @param gameMap map its on
	 * @param body physics
	 * @param type type of char
	 */
	public ServerNpc(ServerGameMap gameMap, Body body, CharacterType type) {
		super(gameMap, body, type);
		
	}
	
	/**
	 * @param pts positions of other characters
	 * @param delta time
	 * @param selfPosition index in pts of self
	 */
	public void update(PositionAndType pts[], float delta, int selfPosition) {
		
		if(dead(delta))
			return;
		
		if(falling(delta))
			return;
		
		float forceX = 0;
		float forceY = 0;
		int left = 0, right = 0, up = 0, down = 0;
		/* Create vector for aggro target. */
		Vector2 closest = new Vector2(-1, -1); // dummy vector
		/* Find closest aggro target by cycling through vectors[]. */
		for(int i = 0; i < pts.length; i++) {
			if (selfPosition == i || this.type == pts[i].type)
				continue;
			if ((Math.abs(pts[i].vector.x - this.body.getPosition().x) +
				 Math.abs(pts[i].vector.y - this.body.getPosition().y) <
				 Math.abs(closest.x - this.body.getPosition().x) +
				 Math.abs(closest.y - this.body.getPosition().y)))
				closest = pts[i].vector;
		}
		
		if (closest.x == -1 && closest.y == -1) // basically, if there are no enemies
			// wander();
			return;
		
		float distance = (float) Math.sqrt(Math.pow((closest.x - this.body.getPosition().x), 2)
								          -Math.pow((closest.y - this.body.getPosition().y), 2));

		/* Test if closest target is within aggro range. Moves if so. */
		if (/*distance <= 30f*/ true)
		{
			double angle;
			/* Calculate angle. */
			angle = Math.atan2(closest.y - this.body.getPosition().y, closest.x - this.body.getPosition().x);
			// had to fix some issues with the Math.atan2 function returning bogus numbers
			if ((int)closest.x - (int)this.body.getPosition().x == 0) {
				forceX = 0;
			} else if (Math.cos(angle) > 0f) {
				forceX =  .25f * FORCE_CONSTANT;
				right = 1;
			} else if (Math.cos(angle) < 0f) {
				forceX = -(.25f * FORCE_CONSTANT);
				left = 1;
			}
			if ((int)closest.y - (int)this.body.getPosition().y == 0) {
				forceY = 0;
			} else if (Math.sin(angle) > 0f) {
				forceY =  .25f * FORCE_CONSTANT;
				up = 1;
			} else if (Math.sin(angle) < 0f) {
				forceY = -(.25f * FORCE_CONSTANT);
				down = 1;
			}
		}

		this.lastAttack += delta;
		
		/* Attack distance check */
		if (distance <= this.attackDistance)
			attack();

		if (distance <= this.attackDistance && (Math.abs(this.body.getPosition().y - closest.y) <= 3f))
			attack();
		
		if (distance <= this.attackDistance && (Math.abs(this.body.getPosition().x - closest.x) <= 3f))
			attack();
		
		/* Modulated code for possible future implementation of a lower-end aggro threshold. */
		//if (distance <= 1f)
		//	forceX = forceY = 0;

		this.movement = !(forceX == 0 && forceY == 0);
		if (this.movement) 
			this.direction = DIRECTIONS[(-down + up)+1][(-left + right)+1];
		//Sys.print("" + this.direction);
		this.prevForceX = forceX;
		this.prevForceY = forceY;
		this.body.applyForceToCenter(forceX, forceY, true);
	}
	
	/**
	 * attack, starts attack phase
	 */
	public void attack() {
		
		if (this.lastAttack > this.attackFrequency) {
			this.trigger = ActionTrigger.NORMAL;
		}
		
		if (this.trigger == ActionTrigger.NORMAL) {
			this.trigger = ActionTrigger.ATTACKING;
			ServerSkill skill = this.skills[0].copy();
			skill.setPerformer(this);
			skill.perform(this);
			this.lastAttack = 0;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see server.entities.ServerCharacter#getState()
	 */
	@Override
	public CharacterState getState() {
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction, this.health, this.trigger);
	}
	
}












