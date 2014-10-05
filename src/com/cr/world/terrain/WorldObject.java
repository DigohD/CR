package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;

public abstract class WorldObject extends Entity implements Renderable{
	
	protected Sprite sprite;
	protected Rectangle rect;

	public WorldObject(Vector2f position) {
		super(position);
		
	}
	
	public abstract void init();
	public abstract void activate();
	

	public void updateRect(){
		rect.setLocation((int)position.x, (int)position.y);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

}
