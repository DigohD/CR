package com.cr.entity.enemy.wisp;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Material;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.attack.Linear;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.world.World;

public class Wisp extends Enemy{

	private class WispLimb extends Entity implements Renderable{

		private Vector2f v, offset;
		private int counter;
		private Sprite limbSprite;
		private boolean dir;
		
		public WispLimb(Vector2f position, boolean dir) {
			super(position);
			limbSprite = new Sprite("wisplimb");
			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
			EntityManager.addEntity(this);
			
			this.dir = dir;
			
			if(dir)
				offset = new Vector2f(-10.5f, 3f);
			else
				offset = new Vector2f(10.5f, -3f);
		}

		@Override
		public void render(Screen screen) {
			screen.renderSprite(limbSprite, position.x, position.y);
		}

		@Override
		public Sprite getSprite() {
			return sprite;
		}

		public void tick(Wisp parent, float dt) {
			counter++;
			
			if(dir)
				v = new Vector2f((float) Math.sin(counter / 5) * 4, (float) Math.cos(counter / 5) * 3f);
			else
				v = new Vector2f((float) -Math.sin(counter / 5) * 4, (float) -Math.cos(counter / 5) * 3f);
			
			offset = offset.add(v);
			position = offset.add(parent.position);
		}
		
		@Override
		public Rectangle getRect() {
			return new Rectangle((int) position.x, (int) position.y, limbSprite.getSpriteWidth(), limbSprite.getSpriteHeight());
		}
		
	}
	
	private int counter;
	private WispLimb wl1, wl2;
	private Vector2f v;
	
	public Wisp(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("wispbody");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
		
//		wl1 = new WispLimb(position, true);
		wl2 = new WispLimb(position, false);
		
		behaviour = new Fleeing(this);
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
		
		v = new Vector2f((float) Math.sin(counter / 30), (float) Math.cos(counter / 10) * 1f);
		position = position.add(v);
		
		counter++;
		if(counter > 60){
			Vector2f range = this.getCenterPos().sub(EntityManager.getHero().getCenterPos());
			float length = Math.abs(range.length());
			if(length < 300){
				Vector2f projVel;
				projVel = Hero.position.sub(position).normalize().mul(35);
				Linear lin = new Linear(position, projVel);
				counter = 0;
			}
		}
		
//		wl1.tick(this, dt);
		wl2.tick(this, dt);
	}

	@Override
	public void death() {
		live = false;
	}

	@Override
	public void HeroCollide(Hero hero) {
		
	}

	@Override
	public void playHurtSound() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Rectangle getRect() {
		return new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}
	
}