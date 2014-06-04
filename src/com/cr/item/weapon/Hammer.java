package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.attack.OneHandAttack;
import com.cr.util.Camera;
import com.cr.util.Vector2f;

public class Hammer extends Weapon{

	private OneHandAttack oneHand;
	
	public Hammer(){
		super("hammer", 0, 0, -3, -10, "Hammer");
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

	public OneHandAttack getOneHand() {
		return oneHand;
	}

	@Override
	public void attack() {
		attack = new OneHandAttack(new Vector2f(x0, x1), rightHand, width, height);
	}
	
	

}
