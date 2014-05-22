package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.object.Renderable;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World implements Renderable{
	
	private final int WORLD_SIZE = 10;
	private Tile[][] terrain = new Tile[WORLD_SIZE][WORLD_SIZE];
	
	public World(){
		for(int i = 0; i < WORLD_SIZE; i++)
			for(int j = 0; j < WORLD_SIZE; j++)
				terrain[i][j] = new GrassTile();
	}

	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < WORLD_SIZE; i++)
			for(int j = 0; j < WORLD_SIZE; j++)
				terrain[i][j].draw(g, i, j);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
}
