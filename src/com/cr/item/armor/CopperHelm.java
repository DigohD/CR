package com.cr.item.armor;

import com.cr.entity.hero.Hero.Direction;
import com.cr.item.Item;

public class CopperHelm extends Item{

	public CopperHelm(){
		super("copperhelm", 0, 0, 0, 0);
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
	public void activateItem(){
		
	}

}
