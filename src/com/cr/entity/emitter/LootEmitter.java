package com.cr.entity.emitter;

import com.cr.crafting.material.Loot;
import com.cr.engine.core.Vector2f;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

public class LootEmitter extends Emitter{

	public LootEmitter(Vector2f position, int lifeTime) {
		super(position, lifeTime);
		EntityManager.addEntity(this);
	}

	@Override
	public void emit() {
		Vector2f initVel = new Vector2f(0, -6);
		initVel = initVel.rotate(Randomizer.getFloat(-30,  30));
		
		new Loot(position, initVel, 101, 1);
	}

}
