package com.cr.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.activation.ItemActive;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;

public abstract class Item implements Renderable{

	private BufferedImage image;
	private int xOffset, yOffset;
	private int vertXOffset, horXOffset;
	protected ItemActive itemActive;
	
	public Item(String imageString, int horXOffset, int vertXOffset, int xOffset, int yOffset){
		image = ImageLoader.getImage(imageString);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
	}
	
	public void render(Graphics2D g, int drawX, int drawY, int spriteID){
		int width = image.getWidth() / 4;
		int height = image.getHeight();
		
		Direction dir = Hero.currentDir;
		int horXOffset = 0;
		
		switch(dir){
			case SOUTH:
				spriteID = 0;
				horXOffset = this.vertXOffset;
				break;
			case EAST:
				spriteID = 1;
				horXOffset = this.horXOffset;
				break;
			case NORTH:
				spriteID = 2;
				horXOffset = -this.vertXOffset;
				break;
			case WEST:
				spriteID = 3;
				horXOffset = -this.horXOffset;
				break;
		}
		
		if(itemActive != null)
			g.drawImage(image,
					// Define position
					drawX + xOffset + horXOffset - (int) Camera.getCamX() + (int) itemActive.getOffset().x,
					drawY + yOffset - (int) Camera.getCamY() + (int) itemActive.getOffset().y,
					drawX + width + xOffset + horXOffset - (int) Camera.getCamX() + (int) itemActive.getOffset().x,
					drawY + height + yOffset - (int) Camera.getCamY() + (int) itemActive.getOffset().y,
					
					//Define Sprite
					spriteID * width, 
					0, 
					(spriteID * width) + width, 
					height, 
					
					// No ImageObserver
					null);
		else
			g.drawImage(image,
					// Define position
					drawX + xOffset + horXOffset - (int) Camera.getCamX(),
					drawY + yOffset - (int) Camera.getCamY(),
					drawX + width + xOffset + horXOffset - (int) Camera.getCamX(),
					drawY + height + yOffset - (int) Camera.getCamY(),
					
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
	public abstract void activateItem();

	public ItemActive getItemActive() {
		return itemActive;
	}
	
	

}
