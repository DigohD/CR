package com.cr.item.weapon;

import com.cr.combat.attack.OneHandAttack;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero.Direction;
import com.cr.util.Randomizer;
import com.cr.util.SoundP;

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
		SoundP.playSound("bladeswing" + (Randomizer.getInt(0, 3) + 1));
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playHitSound() {
		SoundP.playSound("bladehit" + (Randomizer.getInt(0, 2) + 1));
	}

}
