package com.cr.object;

import com.cr.util.Vector2f;

public abstract class DynamicObject extends GameObject implements Tickable, Renderable{

	protected float speedX, speedY;
	protected Vector2f velocity;
	
	public DynamicObject(Vector2f position) {
		super(position);
		
	}

	protected float approach(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}

	protected void move(float dt){
		position = position.add(velocity.mul(dt));
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

}
