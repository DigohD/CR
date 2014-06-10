package com.cr.entity.emitter;

import com.cr.engine.core.Vector2f;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

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
			yVel = impactVelocity.y + Randomizer.getFloat(-10f, 10f) * 0.75f;
			xVel = impactVelocity.x + Randomizer.getFloat(-10f, 10f) * 0.75f;
			Vector2f finalV = new Vector2f(xVel, yVel);
			new Particle(position, finalV, particleSprite, pLifeTime);
		}
	}
}
