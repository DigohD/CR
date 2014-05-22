package com.cr.object.hero;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.game.Game;
import com.cr.object.DynamicObject;
import com.cr.resource.ImageLoader;
import com.cr.util.Vector2f;

public class Hero extends DynamicObject{
	
	private BufferedImage image;
	private Vector2f targetVel;
	
	static Vector2f position = new Vector2f(100, 100);
	
	private Head head;
	
	public Hero() {
		super(position);
		head = new Head();
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(25f, 0);
		image = ImageLoader.getImage("hero");
	}
	
	@Override
	public void tick(float dt) {
//		velocity.x = approach(targetVel.x, velocity.x, dt);
//		velocity.y = approach(targetVel.y, velocity.y, dt);
//		move(dt);
//		if(position.x >= Game.WIDTH/2){
//			targetVel.y = 25f;
//			targetVel.x = 0;
//		}
		
		head.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
//		g.drawImage(image, (int)position.x, (int)position.y, null);
		
		head.render(g);
	}
	
	@Override
	protected void move(float dt){
		position = position.add(velocity.mul(dt));
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
