package com.cr.item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.game.Game;
import com.cr.item.activation.ItemObject;
import com.cr.item.stats.Stat;
import com.cr.item.stats.StatsList;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;
import com.cr.util.Vector2f;

public abstract class Item implements Renderable, Tickable{
	
	protected BufferedImage image;
	protected BufferedImage iconImage;
	
	protected int xOffset, yOffset;
	protected int vertXOffset, horXOffset;
	protected int tempXOffset, tempYOffset;
	
	protected String name;
	protected int width, height;
	
	protected StatsList stats;
	
	public int x0, x1, y0, y1;
	
	protected Vector2f pos;
	
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
		
		stats = new StatsList();
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
		x0 = drawX + xOffset + horXOffset + tempXOffset - (int) Camera.getCamX();
		x1 = drawY + yOffset + tempYOffset - (int) Camera.getCamY();
		y0 = drawX + width + xOffset + horXOffset + tempXOffset - (int) Camera.getCamX();
		y1 = drawY + height + yOffset + tempYOffset - (int) Camera.getCamY();
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
	
	public void renderDescription(Graphics2D g){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		Font font = new Font("Tahoma", 18, 18);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(name, xOffset + 20, yOffset + 40);
		
		stats.render(g, xOffset + 20, yOffset + 80);
	}
	
	public StatsList getStats(){
		return stats;
	}
	
	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}
	
	public abstract boolean renderPrePart(Direction dir);

	public BufferedImage getIconImage() {
		return iconImage;
	}
	
	public void addStat(Stat stat){
		stats.addStat(stat);
	}

	public int getTempXOffset() {
		return tempXOffset;
	}

	public int getTempYOffset() {
		return tempYOffset;
	}

	public Vector2f getPos() {
		int x = x0 + (int) Camera.getCamX();
		int y = x1 + (int) Camera.getCamY();
		return new Vector2f(x, y);
	}
	
	public abstract void activate();
	
	

}
