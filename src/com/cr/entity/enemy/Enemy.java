package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.StatsSheet;
import com.cr.entity.enemy.behaviour.Behaviour;
import com.cr.entity.enemy.behaviour.Chasing;
import com.cr.world.World;

public abstract class Enemy extends Mob implements Collideable{
	
	protected Rectangle rect;
	protected StatsSheet sheet;
	protected Behaviour behaviour;
	
	public Enemy(Vector2f position, World world, StatsSheet sheet) {
		super(position, world);
		this.sheet = sheet;
		
		maxHP = sheet.getMaxHP();
		currentHP = maxHP;
		
		behaviour = new Chasing(this);
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		
	}
	
	@Override
	public Rectangle getRect() {
		return rect;
	}

	public StatsSheet getSheet() {
		return sheet;
	}
	
	

	

}
