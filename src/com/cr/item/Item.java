package com.cr.item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.activation.ItemActive;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;

public abstract class Item implements Renderable{
	protected Rectangle rect;
	
	private BufferedImage image;
	private BufferedImage iconImage;
	
	private int xOffset, yOffset;
	private int vertXOffset, horXOffset;
	protected ItemActive itemActive;
	
	protected int width, height;
	
	public int x0, x1, y0, y1;
	
	public Item(String imageString, int horXOffset, int vertXOffset, int xOffset, int yOffset){
		image = ImageLoader.getImage(imageString);
		iconImage = ImageLoader.getImage(imageString + "icon");
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		
		width = image.getWidth() / 4;
		height = image.getHeight();
	}
	
	public void render(Graphics2D g, int drawX, int drawY, int spriteID){
		
		
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
		
		if(itemActive != null){
			x0 = drawX + xOffset + horXOffset - (int) Camera.getCamX() + (int) itemActive.getOffset().x;
			x1 = drawX + width + xOffset + horXOffset - (int) Camera.getCamX() + (int) itemActive.getOffset().x;
			y0 = drawY + yOffset - (int) Camera.getCamY() + (int) itemActive.getOffset().y;
			y1 = drawY + height + yOffset - (int) Camera.getCamY() + (int) itemActive.getOffset().y;
			g.drawImage(image,
					// Define position
					x0,
					y0,
					x1,
					y1,
					
					//Define Sprite
					spriteID * width, 
					0, 
					(spriteID * width) + width, 
					height, 
					
					// No ImageObserver
					null);
			
		}else{
			int x0 = drawX + xOffset + horXOffset - (int) Camera.getCamX();
			int x1 = drawY + yOffset - (int) Camera.getCamY();
			int y0 = drawX + width + xOffset + horXOffset - (int) Camera.getCamX();
			int y1 = drawY + height + yOffset - (int) Camera.getCamY();
			g.drawImage(image,
					// Define position
					x0,
					x1,
					y0,
					y1,
					
					//Define Sprite
					spriteID * width, 
					0, 
					(spriteID * width) + width, 
					height, 
					
					// No ImageObserver
					null);
			
		
		
			
		}
		
		
			
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
