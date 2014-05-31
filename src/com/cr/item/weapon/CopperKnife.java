package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.attack.OneHandAttack;
import com.cr.util.Camera;
import com.cr.util.Vector2f;

public class CopperKnife extends Weapon{

	public CopperKnife(){
		super("knife", 4, 0, -3, -13, "Knife");
	}

	@Override
	public boolean renderPrePart(Direction dir) {
		switch(dir){
			case SOUTH:
				return false;
			case EAST:
				return true;
			case NORTH:
				return true;
			case WEST:
				return true;
		}
		return false;
	}

	@Override
	public void attack() {
		attack = new OneHandAttack(new Vector2f(x0, x1), rightHand, width, height);
	}
	
	

}
