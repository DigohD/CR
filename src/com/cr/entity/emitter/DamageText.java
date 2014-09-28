package com.cr.entity.emitter;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;

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
	public void render(Screen screen) {
//		Font f = new Font("Tahoma", Font.BOLD, 19);
//		g.setFont(f);
//		g.setColor(Color.BLACK);
//		String dmgS = String.format("%.1f", damage);
//		g.drawString("" + dmgS, position.x - Camera.getCamX(), 
//				position.y - Camera.getCamY());
//		
//		f = new Font("Tahoma", Font.PLAIN, 18);
//		g.setFont(f);
//		g.setColor(Color.WHITE);
//		
//		g.drawString("" + dmgS, position.x + 3 - Camera.getCamX(), 
//				position.y - Camera.getCamY());
	}

	@Override
	public Sprite getSprite() {
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
