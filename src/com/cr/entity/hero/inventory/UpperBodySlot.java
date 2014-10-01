package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.item.armor.Head;
import com.cr.item.armor.UpperBody;

public class UpperBodySlot extends ItemSlot{

	public UpperBodySlot() {
		super(591, 195);
	}
	
	public UpperBodySlot(int xOffset, int yOffset) {
		super(591 + xOffset, 195 + yOffset);
	}
	
	@Override
	public boolean isCompatible(Item item) {
		if(item instanceof UpperBody)
			return true;
		return false;
	}

	@Override
	public Sprite getSprite() {
		return null;
	}

}
