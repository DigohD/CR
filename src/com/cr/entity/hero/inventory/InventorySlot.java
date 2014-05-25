package com.cr.entity.hero.inventory;

import com.cr.item.Item;
import com.cr.item.weapon.MeleeWeapon;

public class InventorySlot extends ItemSlot{

	public InventorySlot(int xIndex, int yIndex){
		super(20 + (xIndex * 50) + (xIndex * 2), 430 + (yIndex * 50) + (yIndex * 2));
	}

	@Override
	public boolean isCompatible(Item item) {
		return true;
	}
}
