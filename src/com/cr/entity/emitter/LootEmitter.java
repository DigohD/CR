package com.cr.entity.emitter;

import com.cr.combat.loot.Loot;
import com.cr.combat.loot.LootTable;
import com.cr.engine.core.Vector2f;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

public class LootEmitter extends Emitter{

	private LootTable lt;
	
	public LootEmitter(Vector2f position, int lifeTime, LootTable lt) {
		super(position, lifeTime);
		this.lt = lt;
		EntityManager.addEntity(this);
	}

	@Override
	public void emit() {
		new Loot(position, new Vector2f(Randomizer.getFloat(-2f, 2f), -7.5f), lt.getLootID(), Randomizer.getInt(1, 5));
	}

}
