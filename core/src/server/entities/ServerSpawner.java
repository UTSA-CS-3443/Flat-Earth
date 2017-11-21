package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utilities.Sys;

public class ServerSpawner
{
	final public static short FOOT_PHYSICS = 0x0001;
	final public static short ATTACK_PHYSICS = 0x0002;
	final public static short WORLD_PHYSICS = 0x0004;
	final public static short BOUNDING_BODY_PHYSICS = 0x0008;
	
	public static <T extends ServerPlayer> T spawnPlayer(Class<T> type, float x, float y, World world)
	{
		ServerPlayer player = null;
		short category = FOOT_PHYSICS; 
		short mask = FOOT_PHYSICS | WORLD_PHYSICS;
		float size = .5f; 
		Body body = createPolyBody(world, x, y, category, mask, 
				new Vector2((size/3)/2,size),
				new Vector2(size/2,size/3),
				new Vector2(size/2,-size/3),
				new Vector2((size/3)/2,-size),
				new Vector2((-size/3)/2,-size),
				new Vector2(-size/2,-size/3),
				new Vector2(-size/2,size/3),
				new Vector2((-size/3)/2,size));
		if(type == ServerKnight.class)
			player = new ServerKnight(body);
		else if(type == ServerWizard.class)
			player = new ServerWizard(body);
		else if(type == ServerArcher.class)
			player = new ServerArcher(body);
		
		return type.cast(player);
	}
	
	public static <T extends ServerNpc> T spawnNpc(Class<T> type, float x, float y, World world)
	{
		ServerNpc player = null;
		short category = FOOT_PHYSICS; 
		short mask = FOOT_PHYSICS | WORLD_PHYSICS;
		float size = .5f; 
		Body body = createPolyBody(world, x, y, category, mask, 
				new Vector2((size/3)/2,size),
				new Vector2(size/2,size/3),
				new Vector2(size/2,-size/3),
				new Vector2((size/3)/2,-size),
				new Vector2((-size/3)/2,-size),
				new Vector2(-size/2,-size/3),
				new Vector2(-size/2,size/3),
				new Vector2((-size/3)/2,size));
		
		player = new ServerNpc(body);
		
		return type.cast(player);
	}
		
	
	public static Body spawnHole(Vector2[] vs, World world) {
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(vs[0].x, vs[0].y);
		def.fixedRotation = false;
		PolygonShape shape = new PolygonShape();
		shape.set(vs);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = .99f;
		fixtureDef.density = 5.0f / 100;
		fixtureDef.filter.categoryBits = WORLD_PHYSICS;
		fixtureDef.filter.maskBits = FOOT_PHYSICS;
		Body body = world.createBody(def).createFixture(fixtureDef).getBody();
		shape.dispose();
		return body;
	}
	
	
	public static Body createPolyBody(World world, float x, float y, short category, short mask, Vector2...vertices)
	{
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x,y);
		def.fixedRotation = false;
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = .99f;
		fixtureDef.density = 5.0f / 100;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		Body body = world.createBody(def).createFixture(fixtureDef).getBody();
		shape.dispose();
		return body;
	}
}

























