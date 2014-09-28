package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

public class Tree extends Entity implements Renderable{

	private Sprite sprite;
	private Rectangle rect;
	
	public Tree(int x , int y){
		super(new Vector2f(x, y));
		int rnd = Randomizer.getInt(1, 4);
		sprite = new Sprite("tree" + rnd);
		rect = new Rectangle(x, y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		
		EntityManager.addEntity(this);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public void updateRect(){
		rect = new Rectangle((int) position.x,(int)  position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}
	
	@Override
	public Rectangle getRect() {
		return rect;
	}
	
}
