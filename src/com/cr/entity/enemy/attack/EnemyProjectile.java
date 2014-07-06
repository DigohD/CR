package com.cr.entity.enemy.attack;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.item.activation.ItemObject;

public abstract class EnemyProjectile extends ItemObject implements Collideable{
	
	public EnemyProjectile(Vector2f pos) {
		super(pos);
	}

	protected Rectangle rect;
	
	public Rectangle getRect() {
		return rect;
	}
	
	public abstract void updateRect();
}
