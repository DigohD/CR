package com.cr.item.weapon.attack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Collideable;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.emitter.DamageText;
import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.StatsSheet;
import com.cr.entity.hero.body.PlayerPart;
import com.cr.game.CollisionManager;
import com.cr.game.EntityManager;
import com.cr.item.activation.ItemObject;
import com.cr.item.activation.Projectile;
import com.cr.item.stats.AffectsDamageDone;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.Weapon;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.util.Vector2f;

public class OneHandAttack extends Projectile implements Renderable{

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
		
		EntityManager.addEntity(this);;
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
	
	@Override
	public void render(Graphics2D g){
		g.setColor(Color.RED);
		g.drawRect(rect.x - (int) Camera.getCamX(), rect.y - (int) Camera.getCamY(), width, height);
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Enemy && !spent){
			Enemy e = (Enemy) obj;
			
			float damage = 0;
			
			for(Stat s : weapon.getStats().getStats()){
				if(s instanceof AffectsDamageDone){
					AffectsDamageDone ad = (AffectsDamageDone) s;
					damage = ad.affectDamage(damage);
				}
			}
			
			e.setHp(e.getHp() - damage);
			
			Vector2f txtPos = new Vector2f(rect.x + width / 2, rect.y + height / 2);
			new DamageText(txtPos, damage);
			new ImpactEmitter(txtPos, 3, "blood", 20, velocity.add(offset.div(4)), 5);
			
			System.out.println(offset);
			
			spent = true;
			live = false;
		}
	}

	@Override
	public void updateRect() {
		rect = weapon.getRect();
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
}
