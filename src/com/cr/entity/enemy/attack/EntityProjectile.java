package com.cr.entity.enemy.attack;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Collideable;
import com.cr.entity.Entity;
import com.cr.entity.Mob;
import com.cr.entity.Tickable;
import com.cr.entity.effect.movement.KnockBack;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.util.RPCalc;

public class EntityProjectile extends EnemyProjectile implements Collideable{
	
	private Entity entity;
	private Enemy entityOwner;
	private Vector2f distance, ret, targetPos;
	private boolean isReturning = false, isActive = false;
	private int width, height, counter, cooldown;
	private float damage = 0;
	
	public EntityProjectile(Entity entity, Enemy owner, Vector2f targetPos, int width, int height, float damage) {
		super(entity.getPosition(), owner);
		this.entity = entity;
		this.targetPos = targetPos;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.entityOwner = owner;
		
		cooldown = 40;
		
		distance = new Vector2f(0, 0);
		ret = new Vector2f(0, 0);
	}

	public void activate(){
		if(!isActive && cooldown <= 0){
			isActive = true;
			live = true;
			EntityManager.addEntity(this);
		}
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Hero){
			Hero h = (Hero) obj;
			new KnockBack(20, 1, h, null, distance.div(50));
			
			float finalDamage = RPCalc.calculateDamage(damage, entityOwner.getSheet(), h.getHeroSheet());
			h.takeDamage(finalDamage);
		}
		
	}

	@Override
	public Rectangle getRect(){
		return new Rectangle((int) entity.getX(), (int) entity.getY(), width, height);
	}

	@Override
	public void tick(float dt) {
		cooldown--;
		if(cooldown < -1)
			cooldown = -1;
		if(isActive){
			counter++;
			if(counter < 25){
				Vector2f dir = Hero.position.sub(entity.getPosition());
				dir = dir.normalize();
				dir = dir;
				
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
				live = false;
				isActive = false;
				isReturning = false;
				counter = 0;
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
