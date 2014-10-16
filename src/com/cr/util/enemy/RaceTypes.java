package com.cr.util.enemy;

public class RaceTypes {

	public static ArcheType[] raceTypes(Race race){
		switch(race){
			case GOBLIN:
				return new ArcheType[]{ArcheType.BRUISER, ArcheType.ASSASIN};
			case WISP:
				return null;
			default:
				return null;
		}
	}
	
}
