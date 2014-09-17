package com.cr.entity.enemy.test;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.effect.movement.KnockBack;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.behaviour.Chasing;
import com.cr.entity.enemy.behaviour.Fleeing;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.util.Randomizer;
import com.cr.world.World;

public class MeleeTest extends Enemy{
	
//	private LootTable lt;
	
	public MeleeTest(Vector2f position, World world) {
		super(position, world);
		sprite = new Sprite("melee");
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		EntityManager.addEntity(this);
		
//		lt = new LootTable();
//		
//		lt.addEntry(new LootEntry(101, 20));
//		lt.addEntry(new LootEntry(102, 20));
//		lt.addEntry(new LootEntry(103, 20));
//		lt.addEntry(new LootEntry(201, 5));
//		lt.addEntry(new LootEntry(202, 5));
//		lt.addEntry(new LootEntry(301, 5));
//		lt.addEntry(new LootEntry(302, 5));
		
		behaviour = new Chasing(this);
	}

	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		behaviour.tick(dt);
		move(dt);
	}

	@Override
	public void death() {
		live = false;
	}

	@Override
	public void HeroCollide(Hero hero) {
		Vector2f CollisionPoint;
		Vector2f CenterOffset = Hero.position.sub(this.position);
		CollisionPoint = CenterOffset.add(position);
		
		ImpactEmitter ie = new ImpactEmitter(CollisionPoint, 1, "white1", 5, velocity, 25);
		KnockBack kb = new KnockBack(20, 1, hero, this, this.getVelocity().div(2));
	}

	@Override
	public void playHurtSound() {
		// TODO Auto-generated method stub
		
	}

//	Vector2f position, int lifeTime, String imageName, 
//	int pLifeTime, Vector2f impactVelocity, int pCount
	
}
