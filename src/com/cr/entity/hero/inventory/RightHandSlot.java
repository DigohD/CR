package com.cr.entity.hero.inventory;

import com.cr.item.Item;
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.MeleeWeapon;

public class RightHandSlot extends ItemSlot{

	public RightHandSlot() {
		super(495, 205);
		item = new Hammer();
	}
	
	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof MeleeWeapon)
			return true;
		return false;
	}

}
