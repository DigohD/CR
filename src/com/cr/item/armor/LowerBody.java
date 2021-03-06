package com.cr.item.armor;

import java.awt.Rectangle;

import com.cr.entity.hero.Hero.Direction;
import com.cr.item.Item;

public abstract class LowerBody extends Item{

	public LowerBody(String imageString, int horXOffset, int vertXOffset,
			int xOffset, int yOffset, String name) {
		super(imageString, horXOffset, vertXOffset, xOffset, yOffset, name);
	}
	
	@Override
	public boolean renderPrePart(Direction dir) {
		switch(dir){
			case SOUTH:
				return false;
			case EAST:
				return false;
			case NORTH:
				return false;
			case WEST:
				return false;
		}
		return true;
	}
	
	@Override
	public Rectangle getRect() {
		return null;
	}

}
