package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.util.SpriteLoader;

public class Tree extends Entity implements Renderable{

	private int objectID;
	private Sprite sprite;
	private Rectangle rect;
	
	public Tree(int x , int y){
		super(new Vector2f(x, y));
		objectID = Randomizer.getInt(1, 4);
		
	}
	
	public void init(){
		sprite = SpriteLoader.getSprite("tree" + objectID);
		rect = new Rectangle((int)getX(), (int)getY(), sprite.getSpriteWidth(), sprite.getSpriteHeight());
		
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

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	
}
