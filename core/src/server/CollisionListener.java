package server;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import communicators.ActionTrigger;
//import server.Skill;
import server.entities.*;
import server.skills.ServerSkill;

/**
 * Hanldes collisions. box2d interface
 * 
 * @author mauricio
 *
 */
public class CollisionListener implements ContactListener {
	// TODO this whole class, once animations are done
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if(fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS)
		{
			((ServerCharacter)fixtureA.getBody().getUserData()).trigger = ActionTrigger.FALLING;
		} else if(fixtureA.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
		{	
			((ServerCharacter)fixtureB.getBody().getUserData()).trigger = ActionTrigger.FALLING;
		} else if(fixtureA.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
		{
			ServerSkill s = (ServerSkill)fixtureA.getBody().getUserData();
			ServerCharacter c = (ServerCharacter)fixtureB.getBody().getUserData();
			if (s.performer == c) {
				return;
			}
			c.health -= s.getPower();
		} else if(fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS)
		{
			ServerSkill s = (ServerSkill)fixtureB.getBody().getUserData();
			ServerCharacter c = (ServerCharacter)fixtureA.getBody().getUserData();
			if (s.performer == c) {
				return;
			}
			c.health -= s.getPower();
		}
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}
}
