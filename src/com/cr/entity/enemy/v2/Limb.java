package com.cr.entity.enemy.v2;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Tickable;

public class Limb extends Entity{

	protected Enemy owner;
	protected Vector2f bodyOffset;

	protected Sprite limbSprite;
	
	public Limb(Vector2f position, Enemy owner, Vector2f bodyOffset, Sprite limbSprite) {
		super(position);
		this.owner = owner;
		this.bodyOffset = bodyOffset;
		this.limbSprite = limbSprite;
	}

	public void tick(float dt) {
		
	}

}
