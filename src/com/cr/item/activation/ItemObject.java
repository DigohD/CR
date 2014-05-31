package com.cr.item.activation;

import com.cr.entity.Entity;
import com.cr.entity.Tickable;
import com.cr.entity.hero.body.PlayerPart;
import com.cr.util.Vector2f;

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
