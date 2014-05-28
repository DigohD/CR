package com.cr.world;

import java.util.HashMap;
import java.util.List;

import com.cr.world.tile.Tile;

public class TileMap {
	
	private int width;
	private int height;
	
	private HashMap<Integer, Tile> tiles;
	
	private List<Integer> pixels;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new HashMap<Integer, Tile>();
	}
	
	public void addTile(int color, Tile tile){
		tiles.put(color, tile);
	}
	

}
