package com.cr.world.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.cr.resource.ImageLoader;

public abstract class Tile{
	
	protected BufferedImage image;
	
	public Tile(String imageString){
		image = ImageLoader.getImage(imageString);
	}
	
	public void draw(Graphics g, int xPos, int yPos){
		g.drawImage(image, xPos, yPos, null);
	}
	
}
