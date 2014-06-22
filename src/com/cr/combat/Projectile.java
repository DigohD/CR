package com.cr.combat;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.item.activation.ItemObject;

public abstract class Projectile extends ItemObject implements Collideable{
	
	public Projectile(Vector2f pos) {
		super(pos);
	}

	protected Rectangle rect;
	
	public Rectangle getRect() {
		return rect;
	}
	
	public abstract void updateRect();
}
