package com.cr.crafting.ui;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class AddButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public AddButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 130, 48));
		sprite = new Sprite("addbutton");
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

	
	
}
