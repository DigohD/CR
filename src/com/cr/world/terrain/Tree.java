package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.game.CollisionManager;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.util.SpriteLoader;

public class Tree extends WorldObject implements Collideable{

	private int objectID;
	
	public Tree(int x , int y){
		super(new Vector2f(x, y));
		//objectID = Randomizer.getInt(1, 4);
		//EntityManager.addByMainThread(this);
	}
	
	public void init(){
		//sprite = SpriteLoader.getSprite("tree" + objectID);
		rect = new Rectangle((int)getX(), (int)getY(), sprite.getSpriteWidth(), sprite.getSpriteHeight());
		//CollisionManager.addworldObject(this);
	}
	
	@Override
	public void activate() {
		
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	@Override
	public void collisionWith(Collideable obj) {
		// TODO Auto-generated method stub
		
	}

}
