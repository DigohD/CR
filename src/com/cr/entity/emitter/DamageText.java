package com.cr.entity.emitter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.util.Vector2f;

public class DamageText extends Entity implements Tickable, Renderable{

	protected Vector2f velocity;
	protected int lifeTime, timeLived;
	protected float damage;
	
	public DamageText(Vector2f position, float damage2) {
		super(position);
		this.damage = damage2;
		velocity = new Vector2f(Randomizer.getFloat(-15, 15), -35f);
		lifeTime = 35;
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		timeLived++;
		if(timeLived > lifeTime){
			live = false;
		}
		velocity = velocity.add(new Vector2f(0, 2f));
		position = position.add(velocity.mul(dt));
	}
	
	@Override
	public void render(Graphics2D g) {
		Font f = new Font("Tahoma", Font.BOLD, 19);
		g.setFont(f);
		g.setColor(Color.BLACK);
		String dmgS = String.format("%.1f", damage);
		g.drawString("" + dmgS, position.x - Camera.getCamX(), 
				position.y - Camera.getCamY());
		
		f = new Font("Tahoma", Font.PLAIN, 18);
		g.setFont(f);
		g.setColor(Color.WHITE);
		
		g.drawString("" + dmgS, position.x + 3 - Camera.getCamX(), 
				position.y - Camera.getCamY());
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	

}
