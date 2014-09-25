package com.cr.combat.attack;

import com.cr.combat.DamagePacket;
import com.cr.combat.Projectile;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Renderable;
import com.cr.entity.effect.movement.KnockBack;
import com.cr.entity.emitter.DamageText;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.game.EntityManager;
import com.cr.item.activation.ItemObject;
import com.cr.item.weapon.Weapon;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.Camera;
import com.cr.util.Randomizer;

public class OneHandAttack extends Projectile{

	private Vector2f velocity;
	private boolean horizontal;
	private int changeTimer, phase, width, height;
	private boolean spent;
	private Weapon weapon;
	
	public OneHandAttack(Vector2f pos, int width, int height, Weapon weapon) {
		super(pos);
		this.width = width;
		this.height = height;
		this.weapon = weapon;
		
		rect = weapon.getRect();
		
		Direction dir = Hero.currentDir;
		switch(dir){
			case SOUTH:
				velocity = new Vector2f(-3f, 10f);
				break;
			case EAST:
				horizontal = true;
				velocity = new Vector2f(8f, -3f);
				break;
			case NORTH:
				velocity = new Vector2f(3f, -10f);
				break;
			case WEST:
				horizontal = true;
				velocity = new Vector2f(-8f, -3f);
				break;
		}
		offset = new Vector2f(0, 0);
		phase = 0;
		changeTimer = 0;
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		changeTimer++;
		if(changeTimer > 3){
			changeTimer = 0;
			if(horizontal)
				switch(phase){
					case 0:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 1:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 2:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 3:
						live = false;
						offset = new Vector2f(0, 0);
						return;
				}
			else
				switch(phase){
					case 0:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 1:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 2:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 3:
						live = false;
						offset = new Vector2f(0, 0);
						return;
				}
		}
		
		offset = offset.add(velocity);
		updateRect();
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Enemy && !spent){
			Enemy e = (Enemy) obj;
			
			ImpactEmitter ie = new ImpactEmitter(new Vector2f(weapon.getPos().x + width / 2, weapon.getPos().y + height / 2), 3, "blood", 12, velocity, 5);
			new KnockBack(20, 1, e, null, getVelocity().div(2));
			
			e.takeDamage(weapon.getDamage());
			
			weapon.playHitSound();
			
//			Vector2f txtPos = new Vector2f(rect.x + width / 2, rect.y + height / 2);
//			new DamageText(txtPos, damage);
//			new ImpactEmitter(txtPos, 3, "blood", 20, velocity.add(offset.div(4)), 5);
			
			spent = true;
			live = false;
		}
	}

	@Override
	public void updateRect() {
		rect = weapon.getRect();
	}
	
}
