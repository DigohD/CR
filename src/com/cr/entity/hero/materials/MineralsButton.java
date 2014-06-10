package com.cr.entity.hero.materials;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class MineralsButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public MineralsButton(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 150, 50));
		sprite = new Sprite("mineralsbutton");
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return null;
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

	
	
}
