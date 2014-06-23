package com.cr.entity.enemy.behaviour;

import com.cr.engine.core.Vector2f;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;

public class Fleeing extends Behaviour{

	public Fleeing(Enemy enemy) {
		super(enemy);
	}

	@Override
	public void tick(float dt){
		Vector2f range = enemy.getCenterPos().sub(EntityManager.getHero().getCenterPos());
		float length = Math.abs(range.length());
		
		range = range.normalize();
		if(length < 280){
			enemy.setVelocity((range.mul(dt).mul(25)));
		}else
			enemy.setVelocity(new Vector2f(0, 0));
	}

}
