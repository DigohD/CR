package com.cr.entity.emitter;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;

public abstract class ParticleEmitter extends Emitter{

	protected Sprite particleSprite;
	protected int pLifeTime;
	
	public ParticleEmitter(Vector2f position, int lifeTime, String imageName, 
			int pLifeTime) {
		super(position, lifeTime);
		this.particleSprite = new Sprite(imageName);
		this.pLifeTime = pLifeTime;
	}

}
