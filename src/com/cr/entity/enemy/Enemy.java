package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.StatsSheet;
import com.cr.world.World;

public abstract class Enemy extends Mob implements Collideable{
	
	protected Rectangle rect;
	protected StatsSheet sheet;
	
	public Enemy(Vector2f position, World world, StatsSheet sheet) {
		super(position, world);
		this.sheet = sheet;
		
		maxHP = sheet.getMaxHP();
		currentHP = maxHP;
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
