package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.engine.input.Input;
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
		if(Input.getMouse(0)){
			System.out.println("Button clicked!");
			if(rect.contains(Input.getMousePosition().toPoint())){
				clicked();
			}
		}
	}

	public abstract void clicked();

	public void setResetsButton(boolean resetsButton) {
		this.resetsButton = resetsButton;
	}
	
	
}
