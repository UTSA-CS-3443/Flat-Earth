package entities.characters;

import com.badlogic.gdx.physics.box2d.Body;

import utilities.GameState;

public class Player extends Character{

	public Player(CharacterType type, Body body) {
		super(type, body);
	}
	
	@Override 
	public void update(float delta, GameState gs) {
		//Animation
		this.frameTime += delta;
		if(this.frameTime > .05f)
		{
			this.frameIndex ++;
			this.frameTime = 0;
		}
		if(this.frameIndex > 3)
		{
			this.frameIndex = 0;
		}
		// all the applyForce stuff if moving the body (physics collision stuff)
		
		float direction = -1;
		synchronized (gs) {
			if (!gs.charMovement()) {
				this.body.setLinearVelocity(0, 0);
				this.body.setAngularVelocity(0);
			} else {
				direction = gs.getCharDirection();
				this.body.applyForceToCenter(gs.getCharForceX(), gs.getCharForceY(), true); // can this always be true> or do i need to set it?
			}
		}
		if(direction >= 0)
		{
			this.body.setTransform(this.body.getPosition(), (float) Math.toRadians(direction));
		}
		
		updateDirection();
		
		// this gets the sprite that will be drawn on screen and binds it to the body (physics bs) by setting it to the bodies position
		// bodys position is the apply force stuff
		this.getFrame().setPosition(this.body.getPosition().x - getFrame().getWidth() / 2, this.body.getPosition().y + getFrame().getHeight());
	}

}
