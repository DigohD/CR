package com.cr.entity.enemy.wisp;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Material;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.emitter.Particle;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.SpellBook;
import com.cr.entity.enemy.attack.Linear;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.Randomizer;
import com.cr.world.World;

public class Wisp extends Enemy{

	private class WispSheet extends StatsSheet{
		public WispSheet(){
			super();
			sheet.put(StatID.LEVEL, new Stat("Level", 1));
			sheet.put(StatID.STRENGTH, new Stat("Strength", 5));
			sheet.put(StatID.AGILITY, new Stat("Agility", 5));
			sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 20));
			sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
			sheet.put(StatID.HP_MAX, new Stat("Max Hp", 5));
			
			sheet.put(StatID.MOVEMENT_SPEED, new Stat("Movement Speed", 1f));
			
			sheet.put(StatID.ARMOR, new Stat("Armor", 15));
			sheet.put(StatID.ARMOR_RATING, new Stat("Armor Rating", 0));
			sheet.put(StatID.PHYSICAL_POWER, new Stat("Physical Power", 0));
			sheet.put(StatID.RAPIDNESS, new Stat("Rapidness", 0));
			sheet.put(StatID.SPELL_POWER, new Stat("Spell Power", 0));
			
			sheet.put(StatID.LIFE_ON_HIT, new Stat("Life On Hit", 0));
			sheet.put(StatID.LIFE_REGEN, new Stat("Life Regen /5s", 5));
			
			updateInternalStats();
			
			sheet.put(StatID.HP_NOW, new Stat("Current Hp", sheet.get(StatID.HP_MAX).getTotal()));
		}
	}
	
	private class WispLimb extends Entity implements Renderable{

		private Vector2f v, offset;
		private float counter;
		private Sprite limbSprite, particle;
		private boolean dir;
		
		public WispLimb(Vector2f position, boolean dir) {
			super(position);
			limbSprite = new Sprite("wisplimb");
			particle = new Sprite("wispp");
			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
			EntityManager.addEntity(this);
			
			this.dir = dir;
			
			if(dir)
				offset = new Vector2f(-10.5f, 3f);
			else
				offset = new Vector2f(25.5f, 3f);
		}

		@Override
		public void render(Screen screen) {
			screen.renderSprite(limbSprite, position.x, position.y);
		}

		@Override
		public Sprite getSprite() {
			return sprite;
		}

		public void tick(Wisp parent, float dt) {
			counter = counter + 0.5f;
			
			if(dir)
				v = new Vector2f((float) Math.sin(counter / 2.5f) * 4f, (float) Math.cos(counter / 2.5f) * 3f);
			else
				v = new Vector2f((float) -Math.sin(counter / 2.5f) * 4f, (float) -Math.cos(counter / 2.5f) * 3f);
			
			offset = offset.add(v);
			position = offset.add(parent.position);
			
			if(Randomizer.getBinary() == 0)
				new Particle(offset.add(parent.position), v.rotate(180).add(new Vector2f(0, 3f)), particle, 20);
			if(Randomizer.getInt(0, 5) == 0)
				new Particle(offset.add(parent.position), new Vector2f(0, 5f), particle, 40);
		}
		
		@Override
		public Rectangle getRect() {
			return new Rectangle((int) position.x, (int) position.y, limbSprite.getSpriteWidth(), limbSprite.getSpriteHeight());
		}
		
	}
	
	private int counter;
	private WispLimb wl1, wl2;
	private Vector2f v;
	
	public Wisp(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("wispbody");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
		
		wl1 = new WispLimb(position, true);
		wl2 = new WispLimb(position, false);
		
		behaviour = new Fleeing(this);
		sheet = new WispSheet();
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
		
		v = new Vector2f((float) Math.sin(counter / 30), (float) Math.cos(counter / 10) * 1f);
		position = position.add(v);
		
		counter++;
		if(counter > 180){
			Vector2f range = this.getCenterPos().sub(EntityManager.getHero().getCenterPos());
			float length = Math.abs(range.length());
			if(length < 300){
				Vector2f projVel;
				projVel = Hero.position.sub(position).normalize().mul(35);
				Linear lin = new Linear(position, projVel, this);
				counter = 0;
			}
		}
		
		wl1.tick(this, dt);
		wl2.tick(this, dt);
	}

	@Override
	public void death() {
		live = false;
	}

	@Override
	public void HeroCollide(Hero hero) {
		
	}

	@Override
	public void playHurtSound() {
		
	}
	
	@Override
	public Rectangle getRect() {
		return new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

	@Override
	public void initSpellBook(SpellBook sb) {
		// TODO Auto-generated method stub
		
	}
	
}
