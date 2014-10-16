package com.cr.entity.enemy.v2.behaviour;

import com.cr.engine.core.Vector2f;
import com.cr.entity.enemy.v2.Enemy;
import com.cr.util.enemy.ArcheType;
import com.cr.util.enemy.EnemyLogic;
import com.cr.util.enemy.Race;

public class Aggressive extends Behaviour{
	
	private float aggroRange;
	
	public Aggressive(float aggroRange, Enemy owner){
		super(owner);
		this.aggroRange = aggroRange;
	}
	
	@Override
	public void tick(float dt){
		if(EnemyLogic.getDistanceToHero(owner.getCenterPos(), EnemyLogic.getNearestHero(owner.getCenterPos())) < aggroRange){
			engaged = true;
			owner.setMoving(true);
			target = EnemyLogic.getNearestHero(owner.getCenterPos());
			owner.setVelocity(EnemyLogic.getDirectionToHero(owner.getCenterPos(), target));
		}else{
			engaged = false;
			owner.setMoving(false);
			owner.setVelocity(new Vector2f(0,0));
			target = null;
		}
			
//		if(engaged){
//			owner.setMoving(true);
//			owner.setMoveDirection(EnemyLogic.getDirectionToHero(owner.getCenterPos(), target));
//		}
	}

	@Override
	public ArcheType[] getCompatibleArcheTypes() {
		return new ArcheType[]{
				ArcheType.BRUISER,
				ArcheType.ASSASIN};
	}

	@Override
	public Race[] getCompatibleRaces() {
		return new Race[]{
				Race.GOBLIN};
	}

}
