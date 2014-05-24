package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.resource.ImageLoader;

public class Contour implements Renderable{

	private BufferedImage image;
	private int xPos, yPos;
	
	public Contour() {
		image = ImageLoader.getImage("contour");
		this.xPos = 460;
		this.yPos = 30;
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
