package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.item.Item;

public abstract class MeleeWeapon extends Item implements Collideable{
	
	

	public MeleeWeapon(String imageString, int horXOffset, int vertXOffset,
			int xOffset, int yOffset) {
		super(imageString, horXOffset, vertXOffset, xOffset, yOffset);
	
	}
	
	public void tick(float dt){
		rect.setLocation(x0, y0);
		
	}
	
	public Rectangle getRect(){
		return rect;
	}

}
