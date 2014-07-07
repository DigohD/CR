package com.cr.entity.enemy.test;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.Sheets;
import com.cr.entity.enemy.attack.Linear;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.world.World;

public class RangedTest extends Enemy{
	
	int counter;
	
	public RangedTest(Vector2f position, World world) {
		super(position, world, Sheets.dummySheet());
		sprite = new Sprite("ranged");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
		
		behaviour = new Fleeing(this);
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
		
		counter++;
		if(counter > 90){
			Vector2f projVel;
			projVel = Hero.position.sub(position).normalize().mul(35);
			Linear lin = new Linear(position, projVel);
			counter = 0;
		}
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

}
