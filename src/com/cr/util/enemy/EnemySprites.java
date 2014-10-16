package com.cr.util.enemy;

import com.cr.util.SpriteLoader;

public class EnemySprites {

	public static EnemySprite getBodySprite(Race race){
		switch(race){
			case GOBLIN:
				return new EnemySprite("felfbody", 1, 10);
			case WISP:
				break;
			default:
				break;
		}
		return null;
	}
	
	public static EnemySprite getHeadSprite(Race race){
		switch(race){
			case GOBLIN:
				return new EnemySprite("felfhead", 1, 10);
			case WISP:
				break;
			default:
				break;
		}
		return null;
	}
	
	public static EnemySprite getHelmSprite(Race race){
		switch(race){
			case GOBLIN:
				return new EnemySprite("felfhelm", 3, 3);
			case WISP:
				break;
			default:
				break;
		}
		return null;
	}
	
}
