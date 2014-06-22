package com.cr.item.weapon;

import com.cr.combat.attack.OneHandAttack;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero.Direction;

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
		attack = new OneHandAttack(new Vector2f(x0, x1), width, height, this);
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}
	
	

}
