package com.cr.util.enemy;

import com.cr.util.Randomizer;
import com.cr.world.biome.Grasslands;

public class EnemyAssembler{

	public static void getNewEnemy(){
		Race[] possibleRaces = Grasslands.getBiomeRaces();
		Race chosenRace = possibleRaces[Randomizer.getInt(0, possibleRaces.length)];
		
		ArcheType[] possibleTypes = RaceTypes.raceTypes(chosenRace);
		ArcheType chosenType = possibleTypes[Randomizer.getInt(0, possibleTypes.length)];
		
		
	}
	
}
