package com.cr.item.activation;

import com.cr.entity.Tickable;
import com.cr.entity.hero.body.PlayerPart;
import com.cr.util.Vector2f;

public abstract class ItemActive implements Tickable{

	protected boolean dead;
	protected Vector2f offset;
	
	public ItemActive(){
		dead = false;
	}
	
	public Vector2f getOffset(){
		return offset.clone();
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public abstract Vector2f getVelocity();
}
