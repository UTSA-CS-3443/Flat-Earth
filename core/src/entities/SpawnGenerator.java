package entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SpawnGenerator
{
	final public static short FOOT_PHYSICS = 0x0001;
	final public static short ATTACK_PHYSICS = 0x0002;
	final public static short WORLD_PHYSICS = 0x0004;
	final public static short BOUNDING_BODY_PHYSICS = 0x0008;
	
	public static <T extends Character> T spawnPlayer(Class<T> type, float x, float y, World world)
	{
		Character player = null;
		short category = FOOT_PHYSICS;
		short mask = FOOT_PHYSICS | WORLD_PHYSICS;
		float size = .5f; // TODO Update this for meter conversion.
		Body body = createPolyBody(world, x, y, category, mask, 
				new Vector2((size/3)/2,size),
				new Vector2(size/2,size/3),
				new Vector2(size/2,-size/3),
				new Vector2((size/3)/2,-size),
				new Vector2((-size/3)/2,-size),
				new Vector2(-size/2,-size/3),
				new Vector2(-size/2,size/3),
				new Vector2((-size/3)/2,size));
		
		if(type == Knight.class)
			player = new Knight(body);
		else if(type == Wizard.class)
			player = new Wizard(body);
		else if(type == Archer.class)
			player = new Archer(body);
		
		return type.cast(player);
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
