package com.cr.combat.loot;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.game.EntityManager;

public class Loot extends Entity implements Renderable, Collideable, Tickable{

	private int amount, counter, initY;
	private Sprite sprite;
	private Rectangle rect;
	
	private Vector2f velocity, initVel;
	private boolean landed;
	
	private int lootType;
	
	public Loot(Vector2f pos, Vector2f initVel, int lootType, int amount){
		super(pos);
		this.lootType = lootType;
		this.amount = amount;
		this.velocity = initVel.clone();
		this.initVel = initVel.clone();
		this.initVel.x = 0;
		initY = (int) pos.y;
		
		EntityManager.addByMainThread(this);
	}

	public void init(){
		sprite = MaterialsBox.getMaterial(lootType).getMaterialImage();
		rect = new Rectangle(0, 0, (int) (sprite.getSpriteWidth() * 0.75f), (int) (sprite.getSpriteHeight() * 0.75f));
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y, 1, 1, 0.75f, 0.75f);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void collisionWith(Collideable obj) {
		if(live && landed && obj instanceof Hero){
			ImpactEmitter ie = new ImpactEmitter(position, 3, "white1", 8, new Vector2f(0, 0), 10);
			MaterialsBox.getMaterial(lootType).addAmount(amount);
//			SoundP.playSound("lootpickup");
			live = false;
		}
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void tick(float dt) {
		if(landed){
			position.y = (float) (position.y + Math.sin(counter / 5));
			counter++;
			if(counter > 360 * 5)
				counter = 0;
		}else{
			position = position.add(velocity);
			velocity.y = velocity.y - ((initVel.y/5) * dt);
			if(position.y > initY)
				landed = true;
		}
		
		rect.x = (int) position.x;
		rect.y = (int) position.y;
	}
	
}
