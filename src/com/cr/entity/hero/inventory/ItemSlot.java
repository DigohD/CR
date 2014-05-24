package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.item.Item;
import com.cr.resource.ImageLoader;

public abstract class ItemSlot implements Renderable, Tickable{

	protected BufferedImage slotImage;
	protected BufferedImage itemImage;
	protected int xPos, yPos;
	
	protected Item item;
	
	public ItemSlot(int xPos, int yPos){
		slotImage = ImageLoader.getImage("slot");
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(slotImage, xPos, yPos, null);
		if(item != null)
			g.drawImage(item.getIconImage(), xPos, yPos, null);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
}
