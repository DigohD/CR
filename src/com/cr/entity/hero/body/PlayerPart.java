package com.cr.entity.hero.body;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.anim.Bob;
import com.cr.entity.hero.anim.HeadBob;
import com.cr.resource.ImageLoader;

public abstract class PlayerPart implements Renderable, Tickable{

	private BufferedImage image;
	private Bob bob;
	
	private int width, height;
	private int horXOffset, vertXOffset, xOffset, yOffset;
	
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
		
		
		g.drawImage(image,
				// Define position
				x + (int)bob.getOffset().x + horXOffset + xOffset,
				y + (int)bob.getOffset().y + vertXOffset + yOffset,
				x + width + (int)bob.getOffset().x + horXOffset + xOffset,
				y + height + (int)bob.getOffset().y + vertXOffset + yOffset,
				
				//Define Sprite
				spriteID * width, 
				0, 
				(spriteID * width) + width, 
				height, 
				
				// No ImageObserver
				null);
	}

	@Override
	public void tick(float dt) {
		bob.tick(dt);
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

	public Bob getBob() {
		return bob;
	}
	
	
	
}
