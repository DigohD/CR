package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.item.Item;
import com.cr.resource.ImageLoader;

public abstract class ItemSlot extends Button implements Renderable, Tickable{

	protected BufferedImage slotImage;
	protected BufferedImage itemImage;
	protected int xPos, yPos;
	
	protected Item item;
	
	public ItemSlot(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 50, 50));
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@Override
	public void clicked(){
		Inventory.buttonClicked(this);
	}
	
	public abstract boolean isCompatible(Item item);

	
}
