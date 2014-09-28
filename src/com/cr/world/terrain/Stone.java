package com.cr.world.terrain;

import java.awt.Rectangle;
import java.util.Vector;

import com.cr.combat.loot.LootEntry;
import com.cr.combat.loot.LootTable;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Material;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.emitter.LootEmitter;
import com.cr.entity.hero.Hero;
import com.cr.game.CollisionManager;
import com.cr.game.EntityManager;
import com.cr.item.weapon.Weapon;
import com.cr.util.Randomizer;

public class Stone extends Entity implements Renderable, Collideable, Tickable{

	private Sprite sprite;
	private Rectangle rect;
	private Vector2f v;
	private int shiverCount = 0;
	private int hitCount = Randomizer.getInt(4, 5);
	
	private LootTable lt;
	
	public Stone(int x , int y){
		super(new Vector2f(x, y));
		sprite = new Sprite("stone");
		rect = new Rectangle(x, y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		EntityManager.addEntity(this);
		CollisionManager.addHitable(this);
		v = new Vector2f(0, 0);
		
		lt = new LootTable();
		
		lt.addEntry(new LootEntry(1, 5));
		lt.addEntry(new LootEntry(2, 10));
		lt.addEntry(new LootEntry(3, 10));
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void tick(float dt) {
		if(v.x != 0 && shiverCount++ == 3){
			if(v.x >= 0){
				v = v.add(new Vector2f(-0.4f, 0));
				v = new Vector2f(-v.x, 0);
			}else{
				v = v.add(new Vector2f(0.4f, 0));
				v = new Vector2f(-v.x, 0);
			}
			if(v.length() <= 0.2f){
				v = new Vector2f(0,0);
				position.x = position.x - 2.0f;
			}
			shiverCount = 0;
		}
		position = position.add(v);
		rect.x = (int) position.x;
		rect.y = (int) position.y;
	}
	
	@Override
	public void collisionWith(Collideable coll) {
		if(v.x == 0 && v.y == 0){
			if(v.x >= 0)
				v = v.add(new Vector2f(3.6f, 0));
			else
				v = v.add(new Vector2f(-3.6f, 0));
			
			Vector2f CollisionPoint = new Vector2f(position.x + 40, position.y + 40);
			
			new LootEmitter(CollisionPoint, 1, lt);
			
			ImpactEmitter ie = new ImpactEmitter(CollisionPoint, 1, "white1", 5, new Vector2f(0,-2.5f), 25);
			hitCount--;
			if(hitCount == 0){
				new LootEmitter(CollisionPoint, 5, lt);
				live = false;
			}
		}
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

	
	
}
