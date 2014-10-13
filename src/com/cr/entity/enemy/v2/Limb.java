package com.cr.entity.enemy.v2;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Entity;
import com.cr.entity.Tickable;

public class Limb extends Entity{

	protected Enemy owner;
	protected Vector2f bodyOffset;

	public Limb(Vector2f position, Enemy owner, Vector2f bodyOffset) {
		super(position);
		this.owner = owner;
		this.bodyOffset = bodyOffset;
	}

	public void tick(float dt) {
		
	}

}
