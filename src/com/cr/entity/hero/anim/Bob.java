package com.cr.entity.hero.anim;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Tickable;

public abstract class Bob implements Tickable{

	protected boolean isGoingUp = false, isGoingRight = false;
	protected Vector2f offset = new Vector2f(0, 0);
	
	protected Vector2f yVelocity;
	protected Vector2f xVelocity;
	
	protected float amplitude;
	protected boolean isActive;
	
	public Bob(boolean up, boolean right, Vector2f yV, Vector2f xV, float amplitude){
		isGoingUp = up;
		isGoingRight = right;
		yVelocity = yV;
		xVelocity = xV;
		this.amplitude = amplitude;
	}
	
	@Override
	public void tick(float dt) {
		if(!isActive)
			dt = dt / 4;
		
		if(isGoingUp)
			offset = offset.sub(yVelocity.mul(dt));
		else
			offset = offset.add(yVelocity.mul(dt));
		
		if(isGoingRight)
			offset = offset.add(xVelocity.mul(dt));
		else
			offset = offset.sub(xVelocity.mul(dt));
		
		if(offset.y > amplitude)
			isGoingUp = true;
		else if(offset.y < -amplitude)
			isGoingUp = false;
		
		if(offset.x > amplitude)
			isGoingRight = false;
		else if(offset.x < -amplitude)
			isGoingRight = true;
	}

	public Vector2f getOffset() {
		if(!isActive)
			return new Vector2f(0, offset.y);
		else
			return offset;
	}
	
	public void setActive(boolean active){
		isActive = active;
	}
	
}
