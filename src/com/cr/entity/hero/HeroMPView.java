package com.cr.entity.hero;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.game.EntityManager;

public class HeroMPView extends Entity implements Renderable{
	
	private Sprite sprite;
	private HeroMP heroMP;
	
	public HeroMPView(HeroMP heroMP){
		super(heroMP.getPosition());
		this.heroMP = heroMP;
		sprite = new Sprite("mptest");
		EntityManager.addEntity(this);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, heroMP.getX(), heroMP.getY());
		
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
