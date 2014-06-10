package com.cr.item.weapon;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.weapon.attack.OneHandAttack;

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
		attack = new OneHandAttack(new Vector2f(x0, x1), width, height, this);
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

}
