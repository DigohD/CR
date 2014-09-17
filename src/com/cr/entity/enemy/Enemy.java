package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.enemy.behaviour.Behaviour;
import com.cr.entity.enemy.behaviour.Chasing;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.hero.Hero;
import com.cr.stats.StatsSheet;
import com.cr.world.World;

public abstract class Enemy extends Mob implements Collideable{
	
	protected Rectangle rect;
	protected StatsSheet sheet;
	protected Behaviour behaviour;
	
	public Enemy(Vector2f position, World world) {
		super(position, world);
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Hero){
			HeroCollide((Hero) obj);
		}
	}
	
	@Override
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void push(Vector2f distance){
		position = new Vector2f(position.x + distance.x, position.y + distance.y);
	}
	
	public StatsSheet getSheet() {
		return sheet;
	}
	
	public abstract void HeroCollide(Hero hero);
	
	

	

}
