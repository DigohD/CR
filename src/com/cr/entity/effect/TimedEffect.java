package com.cr.entity.effect;

import com.cr.entity.Entity;
import com.cr.entity.Mob;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;

public abstract class TimedEffect extends Entity implements Tickable{
	
	protected int lifetime;
	protected int applyInterval;
	protected int counter;
	
	protected Mob owner;
	protected Mob giver;
	
	public TimedEffect(int lifetime, int applyInterval, Mob owner, Mob giver) {
		super(null);
		this.lifetime = lifetime;
		this.applyInterval = applyInterval;
		this.owner = owner;
		this.giver = giver;
		
		counter = 0;
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt){
		if(counter % applyInterval == 0)
			applyEffect();
		if(counter++ > lifetime){
			removeEffect();
			live = false;
		}
		particles();
	}
	
	public abstract void applyEffect();
	public abstract void removeEffect();
	protected abstract void particles();
	
	public Mob getOwner() {
		return owner;
	}

	public Mob getGiver() {
		return giver;
	}
	
	
}
