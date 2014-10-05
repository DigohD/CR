package com.cr.entity.emitter;

import com.cr.combat.loot.Loot;
import com.cr.combat.loot.LootTable;
import com.cr.engine.core.Vector2f;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.world.World;

public class LootEmitter extends Emitter{

	private LootTable lt;
	
	public LootEmitter(Vector2f position, int lifeTime, LootTable lt) {
		super(position, lifeTime);
		this.lt = lt;
		EntityManager.addEntity(this);
	}

	@Override
	public void emit() {
		World.spawnLoot((int) position.x, (int) position.y, lt, 1);
	}

}
