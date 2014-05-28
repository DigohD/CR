package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.cr.game.Game;
import com.cr.util.ColorRGBA;
import com.cr.util.LinkedStack;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.PoisonTile;
import com.cr.world.tile.Tile;

public class TileMap {
	
	private int width;
	private int height;
	
	private BufferedImage img;
	
	TileLayer layer1, layer2;
	
	private LinkedStack<TileLayer> tileStack;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		tileStack = new LinkedStack<TileLayer>();
		
		layer1 = new TileLayer(width, height);
		layer2 = new TileLayer(width, height);
		
		layer1.addTile(ColorRGBA.GREEN, new GrassTile());
		layer2.addTile(ColorRGBA.GREEN, new PoisonTile());
		

		for(int i = 0; i < layer1.pixels.length; i++){
			layer1.pixels[i] = ColorRGBA.GREEN;
		}
		
		for(int i = 0; i < layer1.pixels.length; i++){
			layer2.pixels[i] = 0;
		}
	
	}
	
	public TileMap(BufferedImage img){
		this.img = img;

		width = img.getWidth();
		height = img.getHeight();
		
		tileStack = new LinkedStack<TileLayer>();
		
		//tileStack.push(new TileLayer)
		
		layer1 = new TileLayer(img);
		layer2 = new TileLayer(width, height);
		
		layer1.addTile(ColorRGBA.GREEN, new GrassTile());
		layer2.addTile(ColorRGBA.GREEN, new PoisonTile());
		
		for(int i = 0; i < layer1.pixels.length; i++){
			layer1.pixels[i] = ColorRGBA.GREEN;
		}
		
		for(int i = 0; i < layer1.pixels.length; i++){
			layer2.pixels[i] = 0;
		}
		
		layer1.removeTile(10, 10);
	}
	
	
	
	public void renderMap(Graphics2D g, int xScroll, int yScroll){
		int x0 = xScroll / Tile.TILE_WIDTH;
		int x1 = (xScroll + Game.WIDTH + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
		int y0 = yScroll / Tile.TILE_HEIGHT;
		int y1 = (yScroll + Game.HEIGHT + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				if(layer1.validID(x, y))
					if(layer1.getTileID(x, y) == 0) 
						layer2.pixels[x + y*width] = layer2.getTileID();
	
		layer2.renderTileLayer(g, xScroll, yScroll);
		layer1.renderTileLayer(g, xScroll, yScroll);		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
