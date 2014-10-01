package com.cr.entity.ability;

import com.cr.entity.Mob;
import com.cr.entity.Tickable;

public abstract class Ability implements Tickable{

	protected String name;
	protected Mob source;
	protected float cooldown, countDown = 0;
	
	public Ability(String name, Mob source, float cooldown) {
		super();
		this.name = name;
		this.source = source;
		this.cooldown = cooldown;
	}

	@Override
	public void tick(float dt){
		if(countDown > -1)
			countDown--;
	}
	
	public void activate(){
		if(countDown <= 0){
			Mob target = findTarget();
			if(target != null){
				execute(target);
				countDown = cooldown;
			}
		}
	}
	
	protected abstract void execute(Mob target);
	protected abstract Mob findTarget();
	public abstract boolean isAIActivate();
	
}
