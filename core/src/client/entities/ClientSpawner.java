package client.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utilities.Sys;

public class ClientSpawner
{
	final public static short FOOT_PHYSICS = 0x0001;
	final public static short ATTACK_PHYSICS = 0x0002;
	final public static short WORLD_PHYSICS = 0x0004;
	final public static short BOUNDING_BODY_PHYSICS = 0x0008;
	
	public static <T extends ClientCharacter> T spawn(Class<T> type, float x, float y)
	{
		ClientCharacter player = null;
		if(type == ClientKnight.class)
			player = new ClientKnight();
		else if(type == ClientWizard.class)
			player = new ClientWizard();
		else if(type == ClientArcher.class)
			player = new ClientArcher();
		else if (type == ClientNpc.class)
			player = new ClientNpc();
		return type.cast(player);
	}
	
	
}
