package server;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

//import server.Skill;
import server.entities.*;
import server.skills.ServerSkill;
import utilities.ActionTrigger;
import utilities.Sys;

public class CollisionListener implements ContactListener {
	// TODO this whole class, once animations are done
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if(fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS)
		{
			Sys.print("Falling now");
		} else if(fixtureA.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
		{	
			Sys.print("Falling now");	
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

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}
}
