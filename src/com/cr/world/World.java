package com.cr.world;

import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World{
	
	private final int WORLD_SIZE = 100;
	private Tile[][] terrain = new Tile[WORLD_SIZE][WORLD_SIZE];
	
	public World(){
		for(int i = 0; i < WORLD_SIZE; i++)
			for(int j = 0; j < WORLD_SIZE; j++)
				terrain[i][j] = new GrassTile();
	}
	
}
