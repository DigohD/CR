package com.cr.world.misc;

import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Animation;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;
import com.cr.game.Game;

public class FirePlace extends Entity implements Tickable, Renderable{
	
	private Sprite sprite;
	private Rectangle rect;
	private Animation anim;

	public FirePlace(Vector2f position) {
		super(position);
		sprite = new Sprite("fire", 1, 5, 0, 0, Game.shader, new Transform());
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x, (int)position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		anim = new Animation(sprite, 5);
		EntityManager.addEntity(this);
	}
	
	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x, (int)position.y);
		anim.animateRow(0);
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
