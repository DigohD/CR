package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.item.weapon.Weapon;

public class LeftHandSlot extends ItemSlot{

	public LeftHandSlot(){
		super(690, 205);
	}
	
	public LeftHandSlot(int xOffset, int yOffset) {
		super(690 + xOffset, 205 + yOffset);
	}

	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof Weapon)
			return true;
		return false;
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
