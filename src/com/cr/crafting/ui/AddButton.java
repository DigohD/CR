package com.cr.crafting.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;
import com.cr.resource.ImageLoader;

public class AddButton extends Button implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	private boolean isClicked;
	
	public AddButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 130, 48));
		image = ImageLoader.getImage("addbutton");
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, xPos, yPos, null);
	}

	@Override
	public BufferedImage getImage() {
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
