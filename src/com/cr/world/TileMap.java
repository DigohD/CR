package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.cr.game.Game;
import com.cr.resource.ImageLoader;
import com.cr.util.ColorRGBA;
import com.cr.util.LinkedStack;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.SandTile;
import com.cr.world.tile.StoneTile;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private BufferedImage img;
	
	TileLayer stoneLayer, waterLayer, sandLayer, grassLayer;
	
	private List<TileLayer> layerList;
	private LinkedStack<TileLayer> tileStack;
	
//	public TileMap(int width, int height){
//		this.width = width;
//		this.height = height;
//		
//		layerList = new ArrayList<TileLayer>();
//		
//		layer1 = new TileLayer(width, height);
//		layer2 = new TileLayer(width, height);
//		
//		layer1.addTile(ColorRGBA.GREEN, new GrassTile());
//		layer2.addTile(ColorRGBA.GREEN, new PoisonTile());
//		
//
//		for(int i = 0; i < layer1.pixels.length; i++){
//			layer1.pixels[i] = ColorRGBA.GREEN;
//		}
//		
//		for(int i = 0; i < layer1.pixels.length; i++){
//			layer2.pixels[i] = 0;
//		}
//	
//	}
	
	public TileMap(){
		
		
		stoneLayer = new TileLayer(ImageLoader.getImage("stonelayer"));
		waterLayer = new TileLayer(ImageLoader.getImage("waterlayer"));
		sandLayer = new TileLayer(ImageLoader.getImage("sandlayer"));
		grassLayer = new TileLayer(ImageLoader.getImage("grasslayer"));
		
		stoneLayer.addTile(ColorRGBA.GRAY, new StoneTile());
		waterLayer.addTile(ColorRGBA.BLUE, new WaterTile());
		sandLayer.addTile(ColorRGBA.ORANGE, new SandTile());
		grassLayer.addTile(ColorRGBA.GREEN, new GrassTile());
		
		grassLayer.removeTile(10, 10);
		sandLayer.removeTile(50, 50);
	}
	
	
	
	public void renderMap(Graphics2D g, int xScroll, int yScroll){
		
		stoneLayer.renderTileLayer(g, xScroll, yScroll);
		waterLayer.renderTileLayer(g, xScroll, yScroll);
		sandLayer.renderTileLayer(g, xScroll, yScroll);
		grassLayer.renderTileLayer(g, xScroll, yScroll);
		
//		int x0 = xScroll / Tile.TILE_WIDTH;
//		int x1 = (xScroll + Game.WIDTH + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
//		int y0 = yScroll / Tile.TILE_HEIGHT;
//		int y1 = (yScroll + Game.HEIGHT + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
		
//		for(int y = y0; y < y1; y++)
//			for(int x = x0; x < x1; x++)
//				if(layer1.validID(x, y))
//					if(layer1.getTileID(x, y) == 0) 
//						layer2.pixels[x + y*width] = layer2.getTileID();
	
//		layer2.renderTileLayer(g, xScroll, yScroll);
//		layer1.renderTileLayer(g, xScroll, yScroll);		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
