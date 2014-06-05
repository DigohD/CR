package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.entity.Tickable;
import com.cr.input.Mouse;

public abstract class Button implements Tickable{

	protected Rectangle rect;
	protected boolean resetsButton;
	
	public Button(Rectangle rect){
		this.rect = rect;
		resetsButton = true;
	}
	
	@Override
	public void tick(float dt) {
		if(Mouse.getButton() == 1){
			if(rect.contains(Mouse.getX(), Mouse.getY())){
				clicked();
				if(resetsButton)
					Mouse.resetButton();
			}
		}
	}

	public abstract void clicked();

	public void setResetsButton(boolean resetsButton) {
		this.resetsButton = resetsButton;
	}
	
	
}
