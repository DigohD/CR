package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.resource.ImageLoader;

public class InventoryButton implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	
	public InventoryButton(int xPos, int yPos) {
		super();
		image = ImageLoader.getImage("inventorybutton");
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, xPos, yPos, null);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

}
