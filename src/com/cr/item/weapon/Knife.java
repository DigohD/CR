package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.weapon.attack.OneHand;
import com.cr.util.Camera;

public class Knife extends MeleeWeapon{

	private OneHand oneHand;
	
	public Knife(){
		super("knife", 4, 0, -3, -13, "Knife");
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
		System.out.println("KNIFE COLL");
		
	}
	
	

}
