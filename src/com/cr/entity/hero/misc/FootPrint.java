package com.cr.entity.hero.misc;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;

public class FootPrint extends Entity implements Renderable, Tickable{

	private Sprite sprite;
	private int timer;
	
	public FootPrint(){
		super(EntityManager.getHero().getPos().add(new Vector2f(0, 28)));
		sprite = new Sprite("footprintgrass");
		timer = 0;
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

	@Override
	public void tick(float dt) {
		timer++;
		if(timer > 200)
			live = false;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
