package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.item.Item;
import com.cr.util.Camera;

public abstract class MeleeWeapon extends Item implements Collideable{
	
	

	public MeleeWeapon(String imageString, int horXOffset, int vertXOffset,
			int xOffset, int yOffset) {
		super(imageString, horXOffset, vertXOffset, xOffset, yOffset);
		rect = new Rectangle(x0 + (int)Camera.getCamX(), y0 + (int)Camera.getCamY(), width, height);
	}
	
	public void tick(float dt){
		rect.setLocation(x0 + (int)Camera.getCamX(), y0 + (int)Camera.getCamY());
		
	}
	
	public Rectangle getRect(){
		return rect;
	}

}
