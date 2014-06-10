package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.world.World;

public abstract class Enemy extends Mob implements Collideable{
	
	protected Rectangle rect;
	protected float hp = 300;
	
	public Enemy(Vector2f position, World world) {
		super(position, world);
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		
	}
	
	public abstract void death();
	
	@Override
	public Rectangle getRect() {
		return rect;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}
	
	

	

}
