package com.cr.world;

import java.util.LinkedList;

import com.cr.engine.graphics.ColorRGBA;
import com.cr.world.biome.Grasslands;
import com.cr.world.tile.DirtTile;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.SandTile;
import com.cr.world.tile.StoneTile;
import com.cr.world.tile.WaterTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	private Grasslands grassLands;
	
	public TileMap(LinkedList<Integer> pixels, int width, int height){
		this.width = width;
		this.height = height;
		
		topLayer = new TileLayer(pixels, width, height, 0);
		middleLayer = new TileLayer(pixels, width, height, 0);
		bottomLayer = new TileLayer(pixels, width, height, 0);

		bottomLayer.addTileType(ColorRGBA.BLUE, new WaterTile());
		
		middleLayer.addTileType(ColorRGBA.BROWN, new DirtTile());
		middleLayer.addTileType(ColorRGBA.YELLOW, new SandTile());
		middleLayer.addTileType(ColorRGBA.GRAY, new StoneTile());
		
		topLayer.addTileType(ColorRGBA.GREEN, new GrassTile());
		
		bottomLayer.generateTileLayer();
		middleLayer.generateTileLayer();
		topLayer.generateTileLayer();
	}
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;

		Grasslands g = new Grasslands(width, height);
		this.grassLands = g;
		
		bottomLayer = g.getBottomLayer();
		middleLayer = g.getMiddleLayer();
		topLayer = g.getTopLayer();
		
		bottomLayer.generateTileLayer();
		middleLayer.generateTileLayer();
		topLayer.generateTileLayer();
		
		
		
	}
	
	public void renderMap(){
		bottomLayer.renderTileLayer(true);
		middleLayer.renderTileLayer(false);
		topLayer.renderTileLayer(false);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public TileLayer getBottomLayer() {
		return bottomLayer;
	}

	public TileLayer getMiddleLayer() {
		return middleLayer;
	}

	public TileLayer getTopLayer() {
		return topLayer;
	}

	public Grasslands getGrassLands() {
		return grassLands;
	}
	
}
