package com.cr.entity.enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.cr.game.EntityManager;
import com.cr.resource.ImageLoader;
import com.cr.util.Vector2f;

public class Dummy extends Enemy{
	
	
	
	public Dummy(Vector2f position) {
		super(position);
		image = ImageLoader.getImage("hero");
		width = image.getWidth();
		height = image.getHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		
		
	}

	@Override
	public void death() {
		
		
	}

	
	
	

}
