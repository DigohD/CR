package com.cr.entity.emitter;

import com.cr.crafting.material.loot.Loot;
import com.cr.crafting.material.loot.LootTable;
import com.cr.engine.core.Vector2f;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.util.SoundP;

public class LootEmitter extends Emitter{

	private LootTable lt;
	
	public LootEmitter(Vector2f position, int lifeTime, LootTable lt) {
		super(position, lifeTime);
		this.lt = lt;
		EntityManager.addEntity(this);
	}

	@Override
	public void emit() {
		Vector2f initVel = new Vector2f(0, -6);
		initVel = initVel.rotate(Randomizer.getFloat(-15,  15));
		
		SoundP.playSound("lootdrop" + (Randomizer.getInt(0, 3) + 1));
		
		new Loot(position, initVel, lt.getLootID(), 1);
	}

}
