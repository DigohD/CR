package com.cr.crafting.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;
import com.cr.resource.ImageLoaderOld;

public class CraftButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public CraftButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 130, 48));
		sprite = new Sprite("craftbutton");
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

	
	
}
