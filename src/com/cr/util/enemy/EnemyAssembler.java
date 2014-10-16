package com.cr.util.enemy;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.enemy.v2.Enemy;
import com.cr.entity.enemy.v2.pattern.race.Goblin;
import com.cr.entity.enemy.v2.pattern.race.RacePattern;
import com.cr.stats.StatsSheet;
import com.cr.util.Randomizer;
import com.cr.util.SpriteLoader;
import com.cr.world.World;
import com.cr.world.biome.Grasslands;

public class EnemyAssembler{
	public static Enemy getNewEnemy(World world){
		Enemy e = null;
		
		Race[] possibleRaces = Grasslands.getBiomeRaces();
		Race chosenRace = possibleRaces[Randomizer.getInt(0, possibleRaces.length)];
		
		RacePattern racePattern = null;
		switch(chosenRace){
			case GOBLIN:
				racePattern = new Goblin();
				break;
			case WISP:
				break;
			default:
				break;
		}
		
		e = racePattern.getNewUnit(world);
		return e;
	}
}
