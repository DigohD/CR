package com.cr.entity.emitter;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;

public class Particle extends Entity implements Tickable, Renderable{

	protected Vector2f velocity;
	protected Sprite sprite;
	protected int lifeTime, timeLived;
	protected Rectangle rect;
	
	public Particle(Vector2f position, Vector2f velocity, Sprite sprite,
			int lifeTime) {
		super(position);
		this.velocity = velocity;
		this.sprite = sprite;
		this.lifeTime = lifeTime;
		rect = new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		timeLived++;
		if(timeLived > lifeTime){
			live = false;
		}
		position = position.add(velocity.mul(dt));
		rect.setLocation(position.toPoint());
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
