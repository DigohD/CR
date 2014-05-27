package com.cr.entity.emitter;

import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.util.Vector2f;

public class ImpactEmitter extends ParticleEmitter{
	
	protected Vector2f impactVelocity;
	protected int pCount;
	
	public ImpactEmitter(Vector2f position, int lifeTime, String imageName, 
			int pLifeTime, Vector2f impactVelocity, int pCount) {
		super(position, lifeTime, imageName, pLifeTime);
		this.impactVelocity = impactVelocity;
		this.pCount = pCount;
		EntityManager.addEntity(this);
	}

	@Override
	public void emit() {
		float yVel;
		float xVel;
		for(int i = 0; i < pCount; i++){
			
		}
	}
}
