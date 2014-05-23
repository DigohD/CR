package com.cr.world.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.cr.resource.ImageLoader;

public abstract class Tile{
	
	protected final int TILE_WIDTH = 46, TILE_HEIGHT = 30;
	protected final int TILE_DRAW_OFFSET_X = -7;
	protected final int TILE_DRAW_OFFSET_Y = -5;
	protected BufferedImage image;
	
	public Tile(String imageString){
		image = ImageLoader.getImage(imageString);
	}
	
	public void render(Graphics g, int xPos, int yPos){
		g.drawImage(image, (xPos * TILE_WIDTH) - TILE_DRAW_OFFSET_X, 
				(yPos * TILE_HEIGHT) - TILE_DRAW_OFFSET_Y, null);
	}
	
}
