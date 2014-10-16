package com.cr.entity.enemy.v2.behaviour;

import com.cr.entity.Mob;
import com.cr.entity.enemy.v2.Enemy;
import com.cr.util.enemy.ArcheType;
import com.cr.util.enemy.Race;

public abstract class Behaviour{
	
	protected boolean engaged;
	protected Enemy owner;
	protected Mob target;
	
	public Behaviour(Enemy owner){
		this.owner = owner;
	}
	
	public abstract void tick(float dt);
	
	public abstract ArcheType[] getCompatibleArcheTypes();
	public abstract Race[] getCompatibleRaces();
	
}
