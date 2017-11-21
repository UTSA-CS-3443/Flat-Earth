package server;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

//import server.Skill;
import server.entities.*;
import utilities.ActionState;
import utilities.Sys;

public class CollisionListener implements ContactListener {
	// TODO this whole class, once animations are done
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		// for falling off the map
		if(fixtureA.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS)
		{
			Sys.print("Falling now");
//			ServerCharacter victim = null;
//			Body body = fixtureA.getBody();
//			Sys.print("CollisionListner: THIS IS A TEMPORY FIX, LOGIC.JAVA'S SERVERMAP CANNOT STAY STATIC OR YOU CAN ONLY LAUNCH ONE INSTANCE OF THE SERVER");
//			Sys.print("CollisionListener: also make ServerCharacters body protected again and ServerMaps entitymanagaer protected too. and get ash's method working");
//			for (ServerCharacter c : Logic.map.getEntityManager().characters) {
//				if (body == c.body) {
//					victim = c;
//					break;
//				}
//			}
//			victim.state = ActionState.FALLING;
		} else if(fixtureA.getFilterData().categoryBits == ServerSpawner.HOLE_PHYSICS &&
				fixtureB.getFilterData().categoryBits == ServerSpawner.FOOT_PHYSICS)
		{
//			Sys.print("Falling now");
//			ServerCharacter victim = (ServerCharacter)fixtureB.getBody().getUserData();
//			victim.state = ActionState.FALLING;
			Sys.print("Falling now");
//			ServerCharacter victim = null;
//			Body body = fixtureB.getBody();
//			Sys.print("CollisionListner: THIS IS A TEMPORY FIX, LOGIC.JAVA'S SERVERMAP CANNOT STAY STATIC OR YOU CAN ONLY LAUNCH ONE INSTANCE OF THE SERVER");
//			Sys.print("CollisionListener: also make ServerCharacters body protected again and ServerMaps entitymanagaer protected too. and get ash's method working");
//			for (ServerCharacter c : Logic.map.getEntityManager().characters) {
//				if (body == c.body) {
//					victim = c;
//					break;
//				}
//			}
//			victim.state = ActionState.FALLING;
		}
		
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
