package com.cr.crafting.v2.station;

import java.awt.Point;
import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Focusable;

public class SliderArrow extends Button implements Renderable, Focusable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	private int minX, maxX;
	
	public SliderArrow(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 37, 34));
		minX = xPos;
		maxX = minX + 400;
		sprite = new Sprite("sliderarrow");
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
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

	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
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
