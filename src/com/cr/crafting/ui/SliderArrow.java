package com.cr.crafting.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.Game;
import com.cr.input.Mouse;
import com.cr.resource.ImageLoader;

public class SliderArrow extends Button implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	private boolean isClicked;
	private float ratio;
	
	
	public SliderArrow(int xPos, int yPos) {
		super(new Rectangle(xPos - 20, yPos, 80, 34));
		image = ImageLoader.getImage("sliderarrow");
		this.xPos = xPos;
		this.yPos = yPos;
		resetsButton = false;
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
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		isClicked = true;
		if(Mouse.getX() < xOffset + 565 && Mouse.getX() > xOffset + 35){
			xPos = Mouse.getX() - 19;
			rect = new Rectangle(xPos - 20, yPos, 80, 34);
			float min = xOffset + 35;
			float max = xOffset + 565;
			float sliderLength = max - min;
			float arrowPos = xPos + 19 - min;
			ratio = arrowPos / sliderLength;
		}
	}

	public boolean isClicked() {
		if(isClicked){
//			isClicked = false;
			return true;
		}
		return false;
	}

	public float getRatio() {
		return ratio;
	}
	
	

	
	
}
