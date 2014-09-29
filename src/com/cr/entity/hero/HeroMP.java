package com.cr.entity.hero;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;

public class HeroMP extends Entity implements Tickable, Renderable{
	
	private String userName;
	private Vector2f position;
	private Sprite sprite;
	
	public HeroMP(String userName, Vector2f position){
		super(position);
		this.userName = userName;
		sprite = new Sprite("mptest");
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
	
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
		
		return null;
	}

	public String getUserName() {
		return userName;
	}


	

}
