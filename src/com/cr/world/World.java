package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.gameEngine.EntityManager;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World implements Renderable{
	
	private final int WORLD_SIZE = 100;
	private Tile[][] terrain = new Tile[WORLD_SIZE][WORLD_SIZE];
	private EntityManager eManager;
	
	public World(){
		eManager = new EntityManager();
		for(int i = 0; i < WORLD_SIZE; i++)
			for(int j = 0; j < WORLD_SIZE; j++)
				terrain[i][j] = new GrassTile();
	}
	
	public void tick(float dt){
		eManager.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < WORLD_SIZE; i++)
			for(int j = 0; j < WORLD_SIZE; j++)
				terrain[i][j].draw(g, i, j);
		eManager.render(g);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
}
