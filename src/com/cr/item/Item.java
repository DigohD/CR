package com.cr.item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.game.Game;
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
	
	protected String name;
	protected int width, height;
	
	public int x0, x1, y0, y1;
	
	public Item(String imageString, int horXOffset, int vertXOffset, int xOffset, int yOffset, String name){
		image = ImageLoader.getImage(imageString);
		iconImage = ImageLoader.getImage(imageString + "icon");
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		
		width = image.getWidth() / 4;
		height = image.getHeight();
		
		this.name = name;
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
	
	public void renderDescription(Graphics2D g){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		Font font = new Font("Tahoma", 18, 18);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(name, xOffset + 20, yOffset + 40);
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

	public BufferedImage getIconImage() {
		return iconImage;
	}
	
	
	
	

}
