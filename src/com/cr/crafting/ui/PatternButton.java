package com.cr.crafting.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.crafting.pattern.Pattern;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;
import com.cr.resource.ImageLoader;

public class PatternButton extends Button implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	private boolean isClicked;
	private Pattern pattern;
	
	public PatternButton(int xPos, int yPos, BufferedImage image, Pattern pattern) {
		super(new Rectangle(xPos, yPos, 80, 80));
		this.image = image;
		this.pattern = pattern;
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

	public Pattern getPattern() {
		return pattern;
	}
}
