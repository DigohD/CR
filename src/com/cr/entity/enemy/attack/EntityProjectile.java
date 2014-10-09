package com.cr.entity.enemy.attack;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.Mob;
import com.cr.entity.Tickable;
import com.cr.entity.effect.movement.KnockBack;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.util.RPCalc;

public class EntityProjectile extends EnemyProjectile implements Collideable{
	
	private Entity entity;
	private Enemy entityOwner;
	private Vector2f distance, ret, targetPos;
	private boolean isReturning = false, isActive = false;

	private int width, height, counter, cooldown, cooldownNow;
	private float range;

	private float damage;
	
	public EntityProjectile(Entity entity, Enemy owner, Vector2f targetPos, int width, int height, float damage, float range, int cooldown) {
		super(entity.getPosition(), owner);
		this.entity = entity;
		this.targetPos = targetPos;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.entityOwner = owner;
		this.range = range;
		this.cooldown = cooldown;

		EntityManager.addEntity(this);
		
		distance = new Vector2f(0, 0);
		ret = new Vector2f(0, 0);
	}

	public void activate(){
		if(!isActive && cooldownNow <= 0){
			isActive = true;
			cooldownNow = cooldown;
		}
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		if(isActive && obj instanceof Hero){
			Hero h = (Hero) obj;
//			new KnockBack(20, 1, h, null, distance.div(25));
			
			float finalDamage = RPCalc.calculateDamage(damage, entityOwner.getSheet(), Hero.getHeroSheet());
			System.out.println("Damage " + finalDamage);
			h.takeDamage(finalDamage);
			
			isActive = false;
			
			new ImpactEmitter(owner.getPosition(), 2, "white1", 20, distance, 10);
		}
		
	}

	@Override
	public Rectangle getRect(){
		return new Rectangle((int) entity.getX(), (int) entity.getY(), width, height);
	}

	@Override
	public void tick(float dt) {
		cooldownNow--;
		if(cooldownNow < -1){
			cooldownNow = -1;
			activate();
		}
		if(isActive){
			counter++;
			if(counter < 25){
				Vector2f dir = Hero.position.sub(entity.getPosition());
				dir = dir.normalize();
				
				distance = distance.add(dir);
			}else if(!isReturning){
				ret = distance.div(25);
				isReturning = true;
			}
			
			if(isReturning)
				distance = distance.sub(ret);
						
			if(isReturning && distance.length() <= 10){
				distance = new Vector2f(0, 0);
				ret = new Vector2f(0, 0);
				isReturning = false;
				counter = 0;
				isActive = false;
			}
		}
	}

	public Vector2f getDistance() {
		return distance;
	}

	@Override
	public void updateRect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2f getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
