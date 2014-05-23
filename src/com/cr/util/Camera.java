package com.cr.util;

import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;

public class Camera implements Tickable{
	
	private Hero hero;
	private Vector2f pos, velocity, targetVel;
	private int width, height;
	
	public Camera(Hero hero, int width, int height){
		this.hero = hero;
		this.width = width;
		this.height = height;
		this.pos = new Vector2f(hero.getX() - (width/2), hero.getY() - (height/2));

		velocity = new Vector2f(0,0);
		targetVel = new Vector2f(0,0);
	}
	
	protected float approach(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}
	
	@Override
	public void tick(float dt) {
		
		if(hero.getX() + hero.getWidth() >= pos.x + width){
			targetVel.x = hero.getTargetVel().x;
		}else if(hero.getX() <= pos.x){
			targetVel.x = hero.getTargetVel().x;
		}else if(hero.getY() + hero.getHeight() >= pos.y + height){
			targetVel.y = hero.getTargetVel().y;
		}else if(hero.getY() <= pos.y){
			targetVel.y = hero.getTargetVel().y;
		}else{
			targetVel.y = 0;
			targetVel.x = 0;
		}
		
		velocity.x = approach(targetVel.x, velocity.x, dt*hero.getAccSpeed());
		velocity.y = approach(targetVel.y, velocity.y, dt*hero.getAccSpeed());
		pos = pos.add(velocity.mul(dt));
	}

	public Vector2f getPos() {
		return pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}



	

}
