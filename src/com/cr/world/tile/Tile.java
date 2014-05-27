package com.cr.world.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.resource.ImageLoader;
import com.cr.world.TileLayer;

public abstract class Tile{
	
	public static final int TILE_WIDTH = 46, TILE_HEIGHT = 30;
	public static  final int TILE_DRAW_OFFSET_X = -7;
	public static  final int TILE_DRAW_OFFSET_Y = -5;
	protected BufferedImage image;
	
	protected boolean solid;
	
	public Tile(String imageString){
		image = ImageLoader.getImage(imageString);
		solid = true;
	}
	
	public void render(Graphics2D g, TileLayer tLayer, int xPos, int yPos){
		tLayer.renderTile(g, this, xPos, yPos);
//		g.drawImage(image, (xPos * TILE_WIDTH) - TILE_DRAW_OFFSET_X, 
//				(yPos * TILE_HEIGHT) - TILE_DRAW_OFFSET_Y, null);
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public boolean isWalkable(){
		return solid;
	}
	
}
