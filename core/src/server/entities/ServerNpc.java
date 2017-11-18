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

		float forceX = 0f;
		float forceY = 0f;
		/* Create vector for aggro target. */
		Vector2 closest = vectors[0];
		/* Find closest aggro target by cycling through vectors[]. */
		for(Vector2 target: vectors)
			if ((Math.abs(target.x - this.body.getPosition().x) +
				 Math.abs(target.y - this.body.getPosition().y) <
				 Math.abs(closest.x - this.body.getPosition().x) +
				 Math.abs(closest.y - this.body.getPosition().y)))
				closest = target;

		float distance = (float) Math.sqrt(Math.pow((closest.x - this.body.getPosition().x), 2)
								          -Math.pow((closest.y - this.body.getPosition().y), 2));

		/* Test if closest target is within aggro range. Moves if so. */
		if (distance <= 30f)
		{
			float angle;
			/* Calculate angle. */
			angle = (float) Math.atan2(closest.y - this.body.getPosition().y, closest.x - this.body.getPosition().x);

			if (Math.cos(angle) > 0f)
				forceX =  1f;
			if (Math.cos(angle) < 0f)
				forceX = -1f;
			if (Math.sin(angle) > 0f)
				forceY =  1f;
			if (Math.sin(angle) < 0f)
				forceY = -1f;
		}

		/* Melee attack distance check */
		if (distance <= 2f)
			meleeAttack();

		/* Ranged attack distance check */
		if (distance <= 8f)
			rangedAttack();

		/* Modulated code for possible future implementation of a lower-end aggro threshold. */
		if (distance <= 1f)
			forceX = forceY = 0f;


		this.body.applyForceToCenter(forceX, forceY, true);

		Exit.exit("exiting in Npc.update");
	}

	private void meleeAttack()
	{
		// TODO
	}

	private void rangedAttack()
	{
		// TODO
	}

	@Override
	public CharacterState getState()
	{
		return new CharacterState(this.movement, this.body.getPosition().x, this.body.getPosition().y, this.direction);
	}

}
