package com.cr.entity.hero.inventory;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import com.cr.engine.input.Input;
import com.cr.entity.Tickable;
import com.cr.input.Mouse;

public abstract class Button implements Tickable{

	protected Rectangle rect;
	protected boolean resetsButton;
	
	public Button(Rectangle rect){
		this.rect = rect;
		resetsButton = true;
		Input.addButton(this);
	}
	
	@Override
	public void tick(float dt){

	}

	public void removeFromInput(){
		Input.removeButton(this);
	}
	
	public abstract void clicked();

	public void setResetsButton(boolean resetsButton) {
		this.resetsButton = resetsButton;
	}
	
	public void recieveClick(Point p){
		if(rect.contains(p))
			clicked();
	}
	
}
