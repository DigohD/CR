package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.weapon.attack.OneHand;
import com.cr.util.Camera;

public class Hammer extends MeleeWeapon{

	private OneHand oneHand;
	
	public Hammer(){
		super("hammer", 0, 0, -3, -10, "Hammer");
//		itemActive = new OneHand();
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

	public OneHand getOneHand() {
		return oneHand;
	}

	@Override
	public void activateItem() {
		itemActive = new OneHand();
		System.out.println("Activated");
	}

	@Override
	public void collisionWith(Collideable obj) {
		System.out.println("HAMMER COLL");
		
	}
	
	

}
