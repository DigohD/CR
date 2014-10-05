package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

public class Tree extends WorldObject{

	private int objectID;
	
	public Tree(int x , int y){
		super(new Vector2f(x, y));
		objectID = Randomizer.getInt(1, 4);
		EntityManager.addByMainThread(this);
	}
	
	public void init(){
		sprite = new Sprite("tree" + objectID);
		rect = new Rectangle((int)position.x, (int)position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		
		
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

}
