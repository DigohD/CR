package com.cr.entity.enemy.behaviour;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.Enemy;

public abstract class Behaviour implements Tickable{

	protected Enemy enemy;
	
	public Behaviour(Enemy enemy){
		this.enemy = enemy;
	}
	
	@Override
	public abstract void tick(float dt);
	public abstract void move(float dt, Vector2f direction);
	
}
