package com.cr.entity.emitter;

import java.awt.image.BufferedImage;

import com.cr.resource.ImageLoader;
import com.cr.util.Vector2f;

public abstract class ParticleEmitter extends Emitter{

	protected BufferedImage particleImage;
	protected int pLifeTime;
	
	public ParticleEmitter(Vector2f position, int lifeTime, String imageName, 
			int pLifeTime) {
		super(position, lifeTime);
		this.particleImage = ImageLoader.getImage(imageName);
		this.pLifeTime = pLifeTime;
	}

}
