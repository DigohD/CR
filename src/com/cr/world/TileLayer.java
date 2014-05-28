package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.cr.game.Game;
import com.cr.util.LinkedStack;
import com.cr.util.Randomizer;
import com.cr.world.tile.Tile;

public class TileLayer {
	
	private BufferedImage img;
	private int[] pixels;
	private int width, height;
	
	private LinkedStack<Integer> tileStack;
	private HashMap<Integer, Tile> tiles;
	
	private int xOffset, yOffset;

	public TileLayer(BufferedImage img){
		this.img = img;
		tileStack = new LinkedStack<Integer>();
		tiles = new HashMap<Integer, Tile>();
	
		width = img.getWidth();
		height = img.getHeight();
		pixels = new int[width*height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}
	
	public TileLayer(int width, int height){
		tileStack = new LinkedStack<Integer>();
		tiles = new HashMap<Integer, Tile>();
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void generateRandomLayer(){
		for(int i = 0; i < pixels.length; i++){
			int rn = Randomizer.getInt(0, tiles.size());
			int count = 0;
			for(Integer col : tiles.keySet()){
				if(rn == count){
					pixels[i] = col;
					break;
				}else{
					count++;
				}
			}	
		}
	}
	
	public void generateTileLayer(){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = tileStack.top.data;
	}

	public void addTile(int color, Tile tile){
		tiles.put(color, tile);
	}
	
	public void createTileStack(int...color){
		for(int i = 0; i < color.length; i++){
			tileStack.push(color[i]);
		}
	}
	
	public void removeTile(int x, int y){
		tileStack.pop();
		pixels[x + (y*width)] = tileStack.top.data;
	}
	
	public Tile getTile(int x, int y){
		return tiles.get(pixels[x + (y*width)]);
	}
	
	public boolean shouldRender(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if(tiles.containsKey(pixels[x + (y*width)]))
			return true;
		return false;
	}
	

	
	public void renderTileLayer(Graphics2D g, int xScroll, int yScroll){
		xOffset = xScroll;
		yOffset = yScroll;
		
		int x0 = xScroll / Tile.TILE_WIDTH;
		int x1 = (xScroll + Game.WIDTH + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
		int y0 = yScroll / Tile.TILE_HEIGHT;
		int y1 = (yScroll + Game.HEIGHT + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				if(shouldRender(x, y))
					getTile(x, y).render(g, x, y, xScroll, yScroll);	
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
