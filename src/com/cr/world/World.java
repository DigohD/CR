package com.cr.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.gameEngine.EntityManager;
import com.cr.gameEngine.Game;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World{
	
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

	public void render(Graphics2D g, int xScroll, int yScroll) {
		
		int x0 = xScroll / 46;
		int x1 = (xScroll / 46) + Game.WIDTH/46;
		int y0 = yScroll / 30;
		int y1 = (yScroll / 30) + Game.HEIGHT/30;
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				terrain[x][y].render(g, x, y);
		eManager.render(g);
	}

}
	

