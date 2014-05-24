package com.cr.entity.hero.body;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.anim.Bob;
import com.cr.entity.hero.anim.HeadBob;
import com.cr.item.Item;
import com.cr.item.weapon.Hammer;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;

public abstract class PlayerPart implements Renderable, Tickable{

	protected BufferedImage image;
	protected Bob bob;
	
	protected Item item;
	
	protected int width, height;
	protected int horXOffset, vertXOffset, xOffset, yOffset;
	
	public PlayerPart(String imageString, Bob bob, int horXOffset, int vertXOffset, int xOffset, int yOffset){
		image = ImageLoader.getImage(imageString);
		
		width = image.getWidth() / 4;
		height = image.getHeight();
		
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.bob = bob;
	}
	
	@Override
	public void render(Graphics2D g){
		int x = (int) Hero.position.x;
		int y = (int) Hero.position.y;
		
		Direction dir = Hero.currentDir;
		int spriteID = 0;
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
		
		int drawX = x + (int)bob.getOffset().x + horXOffset + xOffset;
		int drawY = y + (int)bob.getOffset().y + yOffset;
		
		if(item != null && item.renderPrePart(dir))
			item.render(g, drawX, drawY, spriteID);
		
		if(item != null && item.getItemActive() != null)
			g.drawImage(image,
					// Define position
					drawX - (int)Camera.getCamX() + (int) item.getItemActive().getOffset().x,
					drawY - (int)Camera.getCamY() + (int) item.getItemActive().getOffset().y,
					drawX + width - (int)Camera.getCamX() + (int) item.getItemActive().getOffset().x,
					drawY + height - (int)Camera.getCamY() + (int) item.getItemActive().getOffset().y,
					
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
				drawX - (int)Camera.getCamX(),
				drawY - (int)Camera.getCamY(),
				drawX + width - (int)Camera.getCamX(),
				drawY + height - (int)Camera.getCamY(),
				
				//Define Sprite
				spriteID * width, 
				0, 
				(spriteID * width) + width, 
				height, 
				
				// No ImageObserver
				null);
		
		if(item != null && !item.renderPrePart(dir))
			item.render(g, drawX, drawY, spriteID);
	}
	
	@Override
	public void tick(float dt) {
		bob.tick(dt);
		if(item != null && item.getItemActive() != null)
			if(!item.getItemActive().isDead())
				item.getItemActive().tick(dt);
	}
	
	public void activateItem(){
//		System.out.println(item + " : " + item.getItemActive());
		if(item != null)
			item.activateItem();
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

	public Bob getBob() {
		return bob;
	}
	
	public void setItem(Item item){
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
	
	
}
