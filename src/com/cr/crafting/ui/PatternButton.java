package com.cr.crafting.ui;

import java.awt.Rectangle;

import com.cr.crafting.pattern.Pattern;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class PatternButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	private Pattern pattern;
	
	public PatternButton(int xPos, int yPos, Sprite sprite, Pattern pattern) {
		super(new Rectangle(xPos, yPos, 80, 80));
		this.sprite = sprite;
		this.pattern = pattern;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
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

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
