package com.cr.crafting.material.loot;

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
import com.cr.game.EntityManager;
import com.cr.util.SoundP;

public class Loot extends Entity implements Renderable, Collideable, Tickable{

	public Loot(Vector2f position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collisionWith(Collideable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Loot ID's
	 * 
	 * -----------------------------
	 * Bases
	 * -----------------------------
	 * 101 - Copper
	 * 102 - Rugged Cloth
	 * 103 - Scrap Wood
	 * 
	 * -----------------------------
	 * Essences
	 * -----------------------------
	 * 201 - Strange Powder
	 * 202 - Forest Soul
	 * 
	 * -----------------------------
	 * Minerals
	 * -----------------------------
	 * 301 - Pyrite
	 * 302 - Quartz
	 */
//	
//	private int amount, counter, initY;
//	private Sprite sprite;
//	private Rectangle rect;
//	
//	private Vector2f velocity, initVel;
//	private boolean landed;
//	
//	private int lootType;
//	
//	public Loot(Vector2f pos, Vector2f initVel, int lootType, int amount){
//		super(pos);
//		this.lootType = lootType;
//		this.amount = amount;
//		this.velocity = initVel.clone();
//		this.initVel = initVel.clone();
//		this.initVel.x = 0;
//		initY = (int) pos.y;
//		
//		switch(lootType){
//			case 101:
//				sprite = new Sprite("copper");
//				break;
//			case 102:
//				sprite = new Sprite("basiccloth");
//				break;
//			case 103:
//				sprite = new Sprite("scrapwood");
//				break;
//				
//			case 201:
//				sprite = new Sprite("strangepowder");
//				break;
//			case 202:
//				sprite = new Sprite("forestsoul");
//				break;
//				
//			case 301:
//				sprite = new Sprite("pyrite");
//				break;
//			case 302:
//				sprite = new Sprite("quartz");
//				break;
//		}
//		rect = new Rectangle(0, 0, (int) (sprite.getSpriteWidth() * 0.75f), (int) (sprite.getSpriteHeight() * 0.75f));
//		EntityManager.addEntity(this);
//	}
//
//	@Override
//	public void render(Screen screen) {
//		screen.renderSprite(sprite, position.x, position.y, 1, 1, 0.75f, 0.75f);
//	}
//
//	@Override
//	public Sprite getSprite() {
//		return sprite;
//	}
//
//	@Override
//	public void collisionWith(Collideable obj) {
//		if(live && landed && obj instanceof Hero){
//			ImpactEmitter ie = new ImpactEmitter(position, 3, "white1", 8, new Vector2f(0, 0), 10);
//			Hero.getMaterials().addMaterialFromID(lootType, amount);
//			SoundP.playSound("lootpickup");
//			live = false;
//		}
//	}
//
//	@Override
//	public Rectangle getRect() {
//		return rect;
//	}
//
//	@Override
//	public void tick(float dt) {
//		if(landed){
//			position.y = (float) (position.y + Math.sin(counter / 5));
//			counter++;
//			if(counter > 360 * 5)
//				counter = 0;
//		}else{
//			position = position.add(velocity);
//			velocity.y = velocity.y - ((initVel.y/5) * dt);
//			if(position.y > initY)
//				landed = true;
//		}
//		
//		rect.x = (int) position.x;
//		rect.y = (int) position.y;
//	}
	
}
