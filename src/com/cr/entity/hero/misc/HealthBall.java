package com.cr.entity.hero.misc;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.game.EntityManager;

public class HealthBall extends Entity implements Renderable{
	
	private Sprite sprite;
	
	public HealthBall(){
		super(new Vector2f(100, 100));
		EntityManager.addEntity(this);
		sprite = new Sprite("healthfill");
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

}
