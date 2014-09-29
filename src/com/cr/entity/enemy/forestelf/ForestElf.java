package com.cr.entity.enemy.forestelf;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.emitter.Particle;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.attack.Linear;
import com.cr.entity.enemy.behaviour.Chasing;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.enemy.wisp.Wisp;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.Randomizer;
import com.cr.world.World;

public class ForestElf extends Enemy{

	private class ForestElfSheet extends StatsSheet{
		public ForestElfSheet(){
			super();
			sheet.put(StatID.LEVEL, new Stat("Level", 1));
			sheet.put(StatID.STRENGTH, new Stat("Strength", 5));
			sheet.put(StatID.AGILITY, new Stat("Agility", 5));
			sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 20));
			sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
			sheet.put(StatID.HP_MAX, new Stat("Max Hp", 5));
			
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
	
	private class ForestElfHead extends Entity implements Renderable{

		private Sprite headSprite;
		private float counter;
		private Vector2f v, offset;
		
		public ForestElfHead(Vector2f position) {
			super(position);
			headSprite = new Sprite("felfhead");

			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
			EntityManager.addEntity(this);
		}

		public void tick(ForestElf parent, float dt) {
			counter = counter + 0.5f;
			
			v = v.add(new Vector2f(0, Math.sin(a)));
			
			offset = offset.add(v);
			position = offset.add(parent.position);
		}
		
		@Override
		public void render(Screen screen) {
			screen.renderSprite(headSprite, position.x, position.y);
		}

		@Override
		public Sprite getSprite() {
			return headSprite;
		}

		@Override
		public Rectangle getRect() {
			return new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		}
		
	}
	
	private int counter;
	private Vector2f v;
	
	public ForestElf(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("felfbody");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
		
		behaviour = new Chasing(this);
		sheet = new ForestElfSheet();
	}
	
	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
	}

	@Override
	public void HeroCollide(Hero hero) {
		
	}

	@Override
	public void death() {
		
	}

	@Override
	public void playHurtSound() {
		
	}
	
	@Override
	public Rectangle getRect() {
		return new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

}
