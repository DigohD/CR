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
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.util.Vector2f;

public class OneHandAttack extends Projectile implements Collideable, Renderable{

	private Vector2f velocity;
	private boolean horizontal;
	private int changeTimer, phase, width, height;
	private boolean rightHand, spent;
	
	public OneHandAttack(Vector2f pos, boolean rightHand, int width, int height) {
		super(pos);
		this.rightHand = rightHand;
		this.width = width;
		this.height = height;
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
		if(changeTimer > 2){
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
			if(rightHand)
				damage = Randomizer.getFloat(StatsSheet.getrHDamageBase(), 
								StatsSheet.getrHDamageBase() + StatsSheet.getrHDamageDice());
			if(!rightHand)
				damage = Randomizer.getFloat(StatsSheet.getlHDamageBase(), 
								StatsSheet.getlHDamageBase() + StatsSheet.getlHDamageDice());

			System.out.println("Damage: " + damage);
			e.setHp(e.getHp() - damage);
			
			Vector2f txtPos = new Vector2f(rect.x + width / 2, rect.y + height / 2);
			new DamageText(txtPos, damage);
			new ImpactEmitter(txtPos, 3, "blood", 20, velocity.add(offset.div(4)), 5);
			
			System.out.println(offset);
			
			spent = true;
		}
	}

	@Override
	public void updateRect() {
		if(rightHand)
			rect = new Rectangle((int) Hero.getRightHand().getItem().getPos().x + (int) offset.x, 
					(int) Hero.getRightHand().getItem().getPos().y + (int) offset.y, 
					width, height);
		if(!rightHand)
			rect = new Rectangle((int) Hero.getLeftHand().getItem().getPos().x + (int) offset.x, 
					(int) Hero.getLeftHand().getItem().getPos().y + (int) offset.y, 
					width, height);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
}