package server;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

//import server.Skill;
import server.entities.ServerSpawner;

public class CollisionListener implements ContactListener {
	// TODO this whole class, once animations are done
	@Override
	public void beginContact(Contact contact) {
//		Fixture fixtureA = contact.getFixtureA();
//		Fixture fixtureB = contact.getFixtureB();
//		
//		if(fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS &&
//				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
//		{
//			System.out.println("two characters hit each other");
//		}
//		else if(fixtureA.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS &&
//				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
//		{
//			Body attack = fixtureA.getBody();
//			Skill skill = (Skill)attack.getUserData();
//			Character victim = (Character)fixtureB.getBody().getUserData();
//			victim.health -= skill.getPower();
//		}
//		else if(fixtureB.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS &&
//				fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
//		{
//			
//		}
//		else if(fixtureA.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS &&
//				fixtureB.getFilterData().categoryBits == ServerSpawner.WORLD_PHYSICS)
//		{
//			Body attack = fixtureA.getBody();
//			Skill skill = (Skill)attack.getUserData();
//			skill.collideWithWorld();
//		}
//		else if(fixtureB.getFilterData().categoryBits == ServerSpawner.ATTACK_PHYSICS &&
//				fixtureA.getFilterData().categoryBits == ServerSpawner.WORLD_PHYSICS)
//		{
//			Body attack = fixtureB.getBody();
//			Skill skill = (Skill)attack.getUserData();
//			skill.collideWithWorld();
//		}
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
