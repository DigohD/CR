package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.input.Mouse;
import com.cr.resource.ImageLoader;

public class ExitButton extends Button implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	private boolean isClicked;
	
	public ExitButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 150, 50));
		image = ImageLoader.getImage("exitbutton");
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
