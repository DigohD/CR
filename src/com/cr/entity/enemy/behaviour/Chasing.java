package com.cr.entity.enemy.behaviour;

import com.cr.engine.core.Vector2f;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;

public class Chasing extends Behaviour{

	public Chasing(Enemy enemy) {
		super(enemy);
	}

	@Override
	public void tick(float dt) {
		Vector2f range = enemy.getPosition().sub(Hero.position);
		float length = Math.abs(range.length());
		
		if(length < 100){
			new ImpactEmitter(enemy.getPosition(), 3, "blood", 20, new Vector2f(0, 0), 3);
		}
	}

}
