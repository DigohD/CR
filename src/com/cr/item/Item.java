package com.cr.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.resource.ImageLoader;

public abstract class Item implements Renderable{

	private BufferedImage image;
	
	private int xOffset, yOffset;
	
	public Item(String imageString, int xOffset, int yOffset){
		image = ImageLoader.getImage(imageString);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void render(Graphics2D g, int drawX, int drawY, int spriteID){
		int width = image.getWidth() / 4;
		int height = image.getHeight();
		
		g.drawImage(image,
				// Define position
				drawX + xOffset,
				drawY + yOffset,
				drawX + width + xOffset,
				drawY + height + yOffset,
				
				//Define Sprite
				spriteID * width, 
				0, 
				(spriteID * width) + width, 
				height, 
				
				// No ImageObserver
				null);
	}
	
	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}
	
	public abstract boolean renderPrePart(Direction dir);
	
	

}
