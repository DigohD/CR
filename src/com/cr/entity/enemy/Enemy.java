package com.cr.entity.enemy;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.hero.Hero;
import com.cr.item.weapon.MeleeWeapon;
import com.cr.util.Vector2f;

public abstract class Enemy extends Mob implements Collideable{
	
	protected Rectangle rect;

	public Enemy(Vector2f position) {
		super(position);
		
	}
	
	@Override
	public void collisionWith(Collideable obj) {
//		if(obj instanceof Hero){
//			death();
//			live = false;
//		}
		
		if(obj instanceof MeleeWeapon){
			death();
			live = false;
		}
		
	}
	
	public abstract void death();
	
	@Override
	public Rectangle getRect() {
		return rect;
	}

	

}
