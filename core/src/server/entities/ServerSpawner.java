package server.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import server.ServerGameMap;
import utilities.Sys;
import utilities.ParseMap.MapDetails;

public class ServerSpawner
{
	final public static short FOOT_PHYSICS = 0x0001;
	final public static short ATTACK_PHYSICS = 0x0002;
	final public static short WORLD_PHYSICS = 0x0004;
	final public static short BOUNDING_BODY_PHYSICS = 0x0008;
	final public static short HOLE_PHYSICS = 0x0010;
	
	public static <T extends ServerPlayer> T spawnPlayer(Class<T> type, float x, float y, ServerGameMap gameMap)
	{
		ServerPlayer player = null;
		short category = FOOT_PHYSICS; 
		short mask = FOOT_PHYSICS | WORLD_PHYSICS | HOLE_PHYSICS | ATTACK_PHYSICS;
		float size = .5f; 
		Body body = createPolyBody(gameMap.getWorld(), x, y, category, mask, 
				new Vector2((size/3)/2,size),
				new Vector2(size/2,size/3),
				new Vector2(size/2,-size/3),
				new Vector2((size/3)/2,-size),
				new Vector2((-size/3)/2,-size),
				new Vector2(-size/2,-size/3),
				new Vector2(-size/2,size/3),
				new Vector2((-size/3)/2,size));
		if(type == ServerKnight.class)
			player = new ServerKnight(gameMap, body);
		else if(type == ServerWizard.class)
			player = new ServerWizard(gameMap, body);
		else if(type == ServerArcher.class)
			player = new ServerArcher(gameMap, body);
		
		body.setUserData(type.cast(player));
		
		return type.cast(player);
	}
	
	public static <T extends ServerNpc> T spawnNpc(Class<T> type, float x, float y, ServerGameMap gameMap)
	{
		ServerNpc player = null;
		short category = FOOT_PHYSICS; 
		short mask = FOOT_PHYSICS | WORLD_PHYSICS | HOLE_PHYSICS | ATTACK_PHYSICS;
		float size = .5f; 
		Body body = createPolyBody(gameMap.getWorld(), x, y, category, mask, 
				new Vector2((size/3)/2,size),
				new Vector2(size/2,size/3),
				new Vector2(size/2,-size/3),
				new Vector2((size/3)/2,-size),
				new Vector2((-size/3)/2,-size),
				new Vector2(-size/2,-size/3),
				new Vector2(-size/2,size/3),
				new Vector2((-size/3)/2,size));
		
		if(type == ServerNpcKnight.class)
			player = new ServerNpcKnight(gameMap, body);
		else if(type == ServerNpcWizard.class)
			player = new ServerNpcWizard(gameMap, body);
		else if(type == ServerNpcArcher.class)
			player = new ServerNpcArcher(gameMap, body);
		
		body.setUserData(type.cast(player));

		return type.cast(player);
	}
		
	
	public static Body spawnHole(Vector2[] vs, World world) {
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(0, 0);
		def.fixedRotation = false;
		PolygonShape shape = new PolygonShape();
		shape.set(vs);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = .99f;
		fixtureDef.density = 5.0f / 100;
		fixtureDef.filter.categoryBits = HOLE_PHYSICS;
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

	public static Body createWeaponBody(World world, float x, float y, short category, short mask, float range, Vector2...vertices)
	{
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x,y);
		def.linearDamping = 0f;
		def.fixedRotation = true;
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.isSensor = true;
		fixtureDef.friction = .95f;
		fixtureDef.density = 1.0f / 10;
		Body body = world.createBody(def).createFixture(fixtureDef).getBody();
		shape.dispose();
		return body;
	}
}

























