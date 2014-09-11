package com.cr.crafting.v2.station;

import java.awt.Rectangle;

import com.cr.crafting.v2.pattern.Pattern;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class PatternChoice extends Button{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	private Pattern pattern;
	
	public PatternChoice(int xPos, int yPos, Pattern pattern) {
		super(new Rectangle(xPos, yPos, 80, 80));
		sprite = pattern.getSprite();
		this.pattern = pattern;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void clicked() {
		isClicked = true;
		Mouse.resetButton();
	}

	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}

	public Pattern getPattern() {
		return pattern;
	}
	
	
	
}
