package com.cr.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.resource.ImageLoader;

public class Camera2 implements Tickable{
	
	private Hero hero;
	private Vector2f pos, velocity, targetVel, targetPos;
	private int width, height, xOffset = -20, yOffset = -23;
	
	BufferedImage img;
	
	public Camera2(Hero hero, int width, int height){
		this.hero = hero;
		this.width = width;
		this.height = height;
		pos = new Vector2f(hero.getX() - (width/2 - hero.getWidth() - xOffset), hero.getY() - (height/2 - hero.getHeight()-yOffset));

		velocity = new Vector2f(0,0);
		targetVel = new Vector2f(0,0);
		targetPos = new Vector2f(0,0);
		
		img = ImageLoader.getImage("camera");
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
		targetVel.x = hero.getTargetVel().x;
		targetVel.y = hero.getTargetVel().y;
		velocity.x = approach(targetVel.x, velocity.x, dt);
		velocity.y = approach(targetVel.y, velocity.y, dt);
		pos.x = hero.getX() - (width/2 - hero.getWidth()-xOffset) + velocity.x;
		pos.y = hero.getY() - (height/2 - hero.getHeight()-yOffset) + velocity.y;
//
//		Vector2f diff = targetPos.sub(pos).div(15);
//		pos = pos.add(diff);
	}
	
	public void render(Graphics2D g){
		g.drawImage(img, (int)pos.x, (int)pos.y, null);
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
