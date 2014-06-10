package com.cr.item.activation;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Entity;
import com.cr.entity.Tickable;

public abstract class ItemObject extends Entity implements Tickable{

	protected Vector2f offset;
	
	public ItemObject(Vector2f pos){
		super(pos);
		live = true;
	}
	
	public boolean isDead() {
		return !live;
	}
	
	public abstract Vector2f getVelocity();

	public Vector2f getOffset() {
		return offset;
	}
}
