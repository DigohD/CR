package com.cr.crafting.v2.station;

import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Focusable;

public class SliderArrow extends Button implements Focusable{

	private int minX, maxX;
	
	public SliderArrow(int xPos, int yPos) {
		super("sliderarrow", xPos, yPos);
		minX = xPos;
		maxX = minX + 400;
	}

	@Override
	public void clicked() {
		isClicked = true;
		
		xPos = (int) Input.getMousePosition().x - 18;
		if(xPos < minX)
			xPos = minX;
		if(xPos > maxX)
			xPos = maxX;
		rect.x = (int) xPos;
		
		Input.setFocus(this);
		Input.forceRelease();
	}

	public float getPercent(){
		float span = maxX - minX;
		float length = xPos - minX;
		return length / span;
	}
	
	@Override
	public void focus() {
		xPos = (int) Input.getMousePosition().x - 18;
		if(xPos < minX)
			xPos = minX;
		if(xPos > maxX)
			xPos = maxX;
		rect.x = (int) xPos;
	}
	
}
