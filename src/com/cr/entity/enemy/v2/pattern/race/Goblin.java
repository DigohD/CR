package com.cr.entity.enemy.v2.pattern.race;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.enemy.v2.Enemy;
import com.cr.entity.enemy.v2.Limb;
import com.cr.entity.enemy.v2.behaviour.Aggressive;
import com.cr.entity.enemy.v2.motion.AniMotionSet.MotionStatus;
import com.cr.stats.StatsSheet;
import com.cr.util.Randomizer;
import com.cr.util.enemy.AniMotions;
import com.cr.util.enemy.ArcheType;
import com.cr.util.enemy.EnemySprite;
import com.cr.util.enemy.EnemySprites;
import com.cr.util.enemy.Race;
import com.cr.util.enemy.RaceTypes;
import com.cr.world.World;

public class Goblin extends RacePattern{

	@Override
	public Enemy getNewUnit(World world){
		Enemy e;
		
		ArcheType[] possibleTypes = RaceTypes.raceTypes(Race.GOBLIN);
		ArcheType chosenType = possibleTypes[Randomizer.getInt(0, possibleTypes.length)];
		
		EnemySprite bodySprites = EnemySprites.getBodySprite(Race.GOBLIN);
		Sprite bodySprite = new Sprite(bodySprites.getSpriteName(), bodySprites.getRows(), bodySprites.getColumns(),
				Randomizer.getInt(0, bodySprites.getRows()),
				Randomizer.getInt(0, bodySprites.getColumns()),
				World.getShader(),
				new Transform());
		
		e = new Enemy(new Vector2f(0, 0), world, null, bodySprite, "Goblin", new StatsSheet(true));
		
		
		float intensity = Randomizer.getFloat(0.4f, 0.6f);
		e.getMotionSet().addMotion(MotionStatus.IDLE, AniMotions.getBreathing(2, intensity));
		e.getMotionSet().addMotion(MotionStatus.MOVING, AniMotions.getBreathing(3, intensity * 10));
		e.getMotionSet().setActiveMotion(MotionStatus.IDLE);
		e.setBehaviour(new Aggressive(Randomizer.getFloat(275, 325), e));
		
		EnemySprite headSprites = EnemySprites.getHeadSprite(Race.GOBLIN);
		Sprite headSprite = new Sprite(headSprites.getSpriteName(), headSprites.getRows(), headSprites.getColumns(),
				Randomizer.getInt(0, headSprites.getRows()),
				Randomizer.getInt(0, headSprites.getColumns()),
				World.getShader(),
				new Transform());
		
		Limb head = new Limb(e.getPosition(), e, new Vector2f(-13, -40), headSprite);
		head.getMotionSet().addMotion(MotionStatus.IDLE, AniMotions.getBreathing(2, intensity));
		head.getMotionSet().addMotion(MotionStatus.MOVING, AniMotions.getBreathing(3, intensity * 10));
		head.getMotionSet().setActiveMotion(MotionStatus.IDLE);
		
//		if(Randomizer.getInt(0, 2) == 0){
			EnemySprite helmSprites = EnemySprites.getHelmSprite(Race.GOBLIN);
			Sprite helmSprite = new Sprite(helmSprites.getSpriteName(), helmSprites.getRows(), helmSprites.getColumns(),
					Randomizer.getInt(0, helmSprites.getRows()),
					Randomizer.getInt(0, helmSprites.getColumns()),
					World.getShader(),
					new Transform());
			head.setItem(helmSprite);
//		}
		
		e.addLimb(head);
		
		return e;
	}
	
}
