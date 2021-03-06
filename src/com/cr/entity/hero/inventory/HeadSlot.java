package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.item.armor.Head;

public class HeadSlot extends ItemSlot{

	public HeadSlot() {
		super(591, 95);
	}
	
	public HeadSlot(int xOffset, int yOffset) {
		super(591 + xOffset, 95 + yOffset);
	}
	
	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof Head)
			return true;
		return false;
	}

	@Override
	public Sprite getSprite() {
		
		return null;
	}

}
