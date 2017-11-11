package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import communicators.serverToClient.CharacterState;
import utilities.Exit;

public class ServerNpc extends ServerCharacter
{

	public ServerNpc(Body body)
	{
		super(body);
	}

	public void update(Vector2 vectors[])
	{

		Vector2 closest;

		for(Vector2 target: vectors)
		{
			if (Math.abs(vectors[target].x - this.body.getPosition().x) +
					Math.abs(vectors[target].x - this.body.getPosition().y))
			{
				closest = target;
			}
		}

		if (Math.sqrt(closest.x * closest.x + closest.y * closest.y) <= 30f)
		{

		}


		// find closest player, go towards them
		// if close enough to attack, start/keep attacking
		// TODO 
		Exit.exit("exiting in Npc.update");
	}
	
	@Override
	public CharacterState getState()
	{
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction);
	}
	
}
