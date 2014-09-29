package com.cr.entity.enemy.attack;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.enemy.Enemy;
import com.cr.item.activation.ItemObject;

public abstract class EnemyProjectile extends ItemObject implements Collideable{
	
	protected Enemy owner;
	
	public EnemyProjectile(Vector2f pos, Enemy owner) {
		super(pos);
		this.owner = owner;
	}

	protected Rectangle rect;
	
	public Rectangle getRect() {
		return rect;
	}
	
	public abstract void updateRect();
}
