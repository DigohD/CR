package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.item.armor.Head;
import com.cr.item.armor.LowerBody;
import com.cr.item.armor.UpperBody;

public class LowerBodySlot extends ItemSlot{

	public LowerBodySlot() {
		super(591, 265);
	}
	
	public LowerBodySlot(int xOffset, int yOffset) {
		super(591 + xOffset, 265 + yOffset);
	}
	
	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof LowerBody)
			return true;
		return false;
	}

	@Override
	public Sprite getSprite() {
		return null;
	}

}
