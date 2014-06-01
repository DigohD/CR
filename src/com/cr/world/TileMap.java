package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.cr.game.Game;
import com.cr.resource.ImageLoader;
import com.cr.util.ColorRGBA;
import com.cr.util.LinkedStack;
import com.cr.world.biome.Grasslands;
import com.cr.world.tile.DirtTile;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.SandTile;
import com.cr.world.tile.StoneTile;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private BufferedImage img;
	
	TileLayer bottomLayer, middleLayer, topLayer;
	
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
		Grasslands g = new Grasslands();
		
		bottomLayer = g.getBottomLayer();
		middleLayer = g.getMiddleLayer();
		topLayer = g.getTopLayer();
		
//		stoneLayer = new TileLayer(ImageLoader.getImage("stonelayer"));
//		waterLayer = new TileLayer(ImageLoader.getImage("waterlayer"));
//		sandLayer = new TileLayer(ImageLoader.getImage("sandlayer"));
//		grassLayer = new TileLayer(ImageLoader.getImage("grasslayer"));
//		dirtLayer = new TileLayer(ImageLoader.getImage("dirtlayer"));
		
//		stoneLayer.addTileType(ColorRGBA.GRAY, new StoneTile());
//		waterLayer.addTileType(ColorRGBA.BLUE, new WaterTile());
//		sandLayer.addTileType(ColorRGBA.ORANGE, new SandTile());
//		grassLayer.addTileType(ColorRGBA.GREEN, new GrassTile());
//		dirtLayer.addTileType(ColorRGBA.BROWN, new DirtTile());
	
//		grassLayer.removeTile(10, 10);
//		grassLayer.removeTile(11, 10);
//		grassLayer.removeTile(11, 11);
//		grassLayer.removeTile(10, 11);
//		sandLayer.removeTile(50, 50);
//		dirtLayer.removeTile(50, 50);
	}
	
	public void renderMap(Graphics2D g, int xScroll, int yScroll){
		
		int x0 = xScroll / Tile.TILE_WIDTH;
		int x1 = (xScroll + Game.WIDTH + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
		int y0 = yScroll / Tile.TILE_HEIGHT;
		int y1 = (yScroll + Game.HEIGHT + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
		
//		for(int y = y0; y < y1; y++)
//			for(int x = x0; x < x1; x++){
//				if(grassLayer.validID(x, y))
//					if(grassLayer.getTileID(x, y) != 0) 
//						dirtLayer.pixels[x + y*width] = 0;
//					else dirtLayer.pixels[x + y*width] = dirtLayer.getTileID();
//				if(sandLayer.validID(x, y))
//					if(sandLayer.getTileID(x, y) != 0) 
//						dirtLayer.pixels[x + y*width] = 0;
//					else dirtLayer.pixels[x + y*width] = dirtLayer.getTileID();
//				if(dirtLayer.validID(x, y))
//					if(dirtLayer.getTileID(x, y) != 0) 
//						stoneLayer.pixels[x + y*width] = 0;
//					else stoneLayer.pixels[x + y*width] = stoneLayer.getTileID();
//			}
		
		bottomLayer.renderTileLayer(g, xScroll, yScroll);
		middleLayer.renderTileLayer(g, xScroll, yScroll);
		topLayer.renderTileLayer(g, xScroll, yScroll);
		
		
//		stoneLayer.renderTileLayer(g, xScroll, yScroll);
//		dirtLayer.renderTileLayer(g, xScroll, yScroll);
//		waterLayer.renderTileLayer(g, xScroll, yScroll);
//		sandLayer.renderTileLayer(g, xScroll, yScroll);
//		grassLayer.renderTileLayer(g, xScroll, yScroll);		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
