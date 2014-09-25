package com.cr.world;

import com.cr.world.biome.Grasslands;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;

		Grasslands g = new Grasslands(width, height);
		
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
	
}
