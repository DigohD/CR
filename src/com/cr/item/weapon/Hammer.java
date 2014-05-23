package com.cr.item.weapon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.Item;
import com.cr.resource.ImageLoader;

public class Hammer extends Item{

	public Hammer(){
		super("hammer", -3, -10);
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

}
