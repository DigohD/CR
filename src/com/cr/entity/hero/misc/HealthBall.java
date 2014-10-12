package com.cr.entity.hero.misc;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.game.EntityManager;
import com.cr.game.Game;

public class HealthBall extends Entity implements Renderable{
	
	private Sprite sprite;
	
	public HealthBall(){
		super(new Vector2f(100, 100));
		EntityManager.addEntity(this);
		sprite = new Sprite("healthfill", Game.shader);
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

}
