package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.entity.Tickable;
import com.cr.input.Mouse;

public abstract class Button implements Tickable{

	private Rectangle rect;
	
	public Button(Rectangle rect){
		this.rect = rect;
	}
	
	@Override
	public void tick(float dt) {
		if(Mouse.getButton() == 1){
			if(rect.contains(Mouse.getX(), Mouse.getY())){
				clicked();
				Mouse.resetButton();
			}
		}
	}

	public abstract void clicked();
	
	
}
