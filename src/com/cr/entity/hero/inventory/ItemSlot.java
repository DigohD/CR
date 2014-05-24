package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.resource.ImageLoader;

public abstract class ItemSlot implements Renderable, Tickable{

	private BufferedImage slotImage;
	private BufferedImage itemImage;
	private int xPos, yPos;
	
	public ItemSlot(int xPos, int yPos){
		slotImage = ImageLoader.getImage("slot");
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(slotImage, xPos, yPos, null);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		
	}

}
