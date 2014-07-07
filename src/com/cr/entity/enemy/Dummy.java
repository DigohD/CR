package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.world.World;

public class Dummy extends Enemy{
	
	public Dummy(Vector2f position, World world) {
		super(position, world, Sheets.dummySheet());
		sprite = new Sprite("tree1");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
	}

	@Override
	public void death() {
		live = false;
	}

	@Override
	public void HeroCollide(Hero hero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playHurtSound() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
