package com.cr.entity.enemy.attack;

import java.awt.Rectangle;

import com.cr.combat.Projectile;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Renderable;
import com.cr.entity.effect.movement.KnockBack;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.emitter.ParticleEmitter;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.item.weapon.Weapon;

public class Linear extends EnemyProjectile implements Renderable{

	protected Vector2f velocity;
	protected boolean spent;
	protected Sprite sprite;
	protected ImpactEmitter ie;
	
	public Linear(Vector2f pos, Vector2f velocity) {
		super(pos);
		this.velocity = velocity;
		
		sprite = new Sprite("rock1");
		rect = new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		ie = new ImpactEmitter(position, 1, "white1", 5, velocity.normalize().mul(-1), 1);
		
		EntityManager.addEntity(this);
	}

	@Override
	public void collisionWith(Collideable obj) {
		System.out.println("Proj islive: " + live);
		if(obj instanceof Hero){
			Vector2f CollisionPoint;
			Vector2f CenterOffset = Hero.position.sub(this.position);
			CollisionPoint = CenterOffset.add(position);
			
			ImpactEmitter ie = new ImpactEmitter(CollisionPoint, 1, "white1", 5, velocity, 5);
			KnockBack kb = new KnockBack(20, 1, ((Hero) obj), null, this.getVelocity().div(2));
			
			spent = true;
			live = false;
		}
	}

	@Override
	public void tick(float dt) {
		Vector2f distance = velocity.mul(dt);
		position = position.add(distance);
		ie.setPosition(position);
		ie.emit();
		updateRect();
	}

	@Override
	public void updateRect() {
		rect = new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

	@Override
	public Vector2f getVelocity() {
		return velocity;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
}
