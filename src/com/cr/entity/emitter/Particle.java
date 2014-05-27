package com.cr.entity.emitter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.util.Vector2f;

public class Particle extends Entity implements Tickable, Renderable{

	protected Vector2f velocity;
	protected BufferedImage image;
	protected int lifeTime, timeLived;
	
	public Particle(Vector2f position, Vector2f velocity, BufferedImage image,
			int lifeTime) {
		super(position);
		this.velocity = velocity;
		this.image = image;
		this.lifeTime = lifeTime;
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		timeLived++;
		if(timeLived > lifeTime){
			live = false;
		}
		position = position.add(velocity.mul(dt));
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.x - (int)Camera.getCamX(), 
				(int) position.y - (int)Camera.getCamY(), null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	

}
