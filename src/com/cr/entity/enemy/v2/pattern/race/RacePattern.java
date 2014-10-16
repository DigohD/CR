package com.cr.entity.enemy.v2.pattern.race;

import com.cr.entity.enemy.v2.Enemy;
import com.cr.world.World;

public abstract class RacePattern {

	public abstract Enemy getNewUnit(World world);
}
