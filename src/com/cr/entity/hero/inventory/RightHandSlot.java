package com.cr.entity.hero.inventory;

import com.cr.item.Item;
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.Weapon;

public class RightHandSlot extends ItemSlot{

	public RightHandSlot() {
		super(495, 205);
	}
	
	public RightHandSlot(int xOffset, int yOffset) {
		super(495 + xOffset, 205 + yOffset);
	}
	
	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof Weapon)
			return true;
		return false;
	}

}
