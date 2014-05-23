package com.cr.entity;

import com.cr.util.Vector2f;

public abstract class Mob extends Entity implements Tickable, Renderable{

	protected float speedX, speedY;
	protected Vector2f velocity;
	
	public Mob(Vector2f position) {
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
