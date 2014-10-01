package com.cr.entity.enemy.forestelf;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.Mob;
import com.cr.entity.Renderable;
import com.cr.entity.ability.self.Haste;
import com.cr.entity.emitter.Particle;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.SpellBook;
import com.cr.entity.enemy.attack.EntityProjectile;
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
			sheet.put(StatID.STRENGTH, new Stat("Strength", 15));
			sheet.put(StatID.AGILITY, new Stat("Agility", 5));
			sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 5));
			sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 1));
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
	
	private class ForestElfHead extends Entity implements Renderable{
		private Sprite headSprite;
		private float counter;
		private Vector2f v, offset;
		
		public ForestElfHead(Vector2f position) {
			super(position);
			headSprite = new Sprite("felfhead");

			v = new Vector2f(0, 0);
			offset = new Vector2f(0f, 0f);
			
			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
		}

		public void tick(ForestElf parent, float dt) {
			if(parent.isMoving){
				counter = counter + 0.2f;
				v = new Vector2f(0, (float) Math.sin(counter));
			}else{
				counter = counter + 0.05f;
				v = new Vector2f(0, (float) Math.sin(counter) / 4);
			}
			
			
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
			return new Rectangle((int) position.x, (int) position.y + 1, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		}
	}
	
	private class ForestElfRightHand extends Entity{
		
		private Sprite handSprite;
		private float counter;
		private Vector2f v, offset;
		private Enemy parent;
		private EntityProjectile ep;
		
		public ForestElfRightHand(Vector2f position, Enemy parent) {
			super(position);
			handSprite = new Sprite("felfrighthand");
			this.parent = parent;
			
			v = new Vector2f(0, 0);
			offset = new Vector2f(0f, 0f);
			
			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
			
			ep = new EntityProjectile(this, parent, Hero.position, width, height, 5);
		}

		public void tick(ForestElf parent, float dt) {
			if(parent.isMoving){
				ep.activate();
				counter = counter + 0.2f;
				v = new Vector2f(0, (float) Math.cos(counter));
			}else{
				counter = counter + 0.05f;
				v = new Vector2f(0, (float) Math.cos(counter) / 4);
			}
			offset = offset.add(v);
			
			ep.tick(dt);
			
			position = offset.add(parent.position.add(ep.getDistance()));
		}
		
		public void render(Screen screen) {
			screen.renderSprite(handSprite, position.x, position.y);
		}

		public Sprite getSprite() {
			return handSprite;
		}

		public Rectangle getRect() {
			return new Rectangle((int) position.x, (int) position.y + 1, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		}
	}
	
	private class ForestElfLeftHand extends Entity{
		
		private Sprite handSprite;
		private float counter;
		private Vector2f v, offset;
		private Enemy parent;
		private EntityProjectile ep;
		
		public ForestElfLeftHand(Vector2f position, Enemy parent) {
			super(position);
			handSprite = new Sprite("felflefthand");
			this.parent = parent;
			
			v = new Vector2f(0, 0);
			offset = new Vector2f(0f, 0f);
			
			width = sprite.getSpriteWidth();
			height = sprite.getSpriteHeight();
			
			ep = new EntityProjectile(this, parent, Hero.position, width, height, 5);
		}

		public void tick(ForestElf parent, float dt) {
			if(parent.isMoving){
				counter = counter + 0.2f;
				v = new Vector2f(0, (float) -Math.cos(counter));
				ep.activate();
			}else{
				counter = counter + 0.05f;
				v = new Vector2f(0, (float) -Math.cos(counter) / 4);
			}
			offset = offset.add(v);
			
			ep.tick(dt);
			
			position = offset.add(parent.position.add(ep.getDistance()));
		}
		
		public void render(Screen screen) {
			screen.renderSprite(handSprite, position.x, position.y);
		}

		public Sprite getSprite() {
			return handSprite;
		}

		public Rectangle getRect() {
			return new Rectangle((int) position.x, (int) position.y + 1, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		}
		
	}
	
	private float counter;
	private Vector2f v, offset;
	private ForestElfHead head;
	private ForestElfRightHand rightHand;
	private ForestElfLeftHand leftHand;
	
	public ForestElf(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("felfbody");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		
		head = new ForestElfHead(position);
		rightHand = new ForestElfRightHand(position, this);
		leftHand = new ForestElfLeftHand(position, this);
		EntityManager.addEntity(this);
		
		behaviour = new Chasing(this);
		sheet = new ForestElfSheet();
	}
	
	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
		
		counter = counter + 0.4f;
		
		if(isMoving()){
			offset = new Vector2f((float) Math.cos(counter / 2f), (float) Math.sin(counter) * 2);
		}else{
			counter = 0;
			offset = new Vector2f(0, 0);
		}
		
		position = position.add(offset);
		
		head.tick(this, dt);
		rightHand.tick(this, dt);
		leftHand.tick(this, dt);
		
		spellBook.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x , position.y);
		head.render(screen);
		rightHand.render(screen);
		leftHand.render(screen);
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

	@Override
	public void initSpellBook(SpellBook book) {
		book.addSpell(new Haste(this, 600));
	}

}
