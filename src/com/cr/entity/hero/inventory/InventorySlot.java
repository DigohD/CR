package com.cr.entity.hero.inventory;

import com.cr.item.Item;
import com.cr.item.weapon.Weapon;

public class InventorySlot extends ItemSlot{

	public InventorySlot(int xIndex, int yIndex){
		super(20 + (xIndex * 50) + (xIndex * 2), 430 + (yIndex * 50) + (yIndex * 2));
	}
	
	public InventorySlot(int xIndex, int yIndex, int xOffset, int yOffset){
		super(20 + xOffset + (xIndex * 50) + (xIndex * 2), 
				430 + yOffset + (yIndex * 50) + (yIndex * 2));
	}

	@Override
	public boolean isCompatible(Item item) {
		return true;
	}
}
