package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.game.EntityManager;
import com.cr.world.World;

public class Dummy extends Enemy{
	
	
	
	public Dummy(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("hero");
		width = sprite.getWidth();
		height = sprite.getHeight();
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
