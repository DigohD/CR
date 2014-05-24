package com.cr.item.weapon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.Item;
import com.cr.item.weapon.attack.OneHand;
import com.cr.resource.ImageLoader;

public class Knife extends Item{

	private OneHand oneHand;
	
	public Knife(){
		super("knife", 4, 0, -3, -13);
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
	
	

}
