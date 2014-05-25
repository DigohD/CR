package com.cr.entity.hero.inventory;

import com.cr.item.Item;
import com.cr.item.weapon.MeleeWeapon;

public class LeftHandSlot extends ItemSlot{

	public LeftHandSlot(){
		super(690, 205);
	}
	
	public LeftHandSlot(int xOffset, int yOffset) {
		super(690 + xOffset, 205 + yOffset);
	}

	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof MeleeWeapon)
			return true;
		return false;
	}

}
