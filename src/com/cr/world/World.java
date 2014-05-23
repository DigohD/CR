package com.cr.world;

import java.awt.Graphics2D;

import com.cr.entity.hero.Hero;
import com.cr.gameEngine.EntityManager;
import com.cr.gameEngine.Game;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;
import com.cr.util.ColorRGBA;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World{
	
	private TileLayer tLayer;
	private EntityManager eManager;
	
	private Hero hero;
	private Camera camera;
	int xScroll, yScroll;
	
	private int width, height;
	public World(){
		eManager = new EntityManager();
		hero = new Hero(this);
		camera = new Camera(0, 0);
		tLayer = new TileLayer(ImageLoader.getImage("tileLayer"));
		width = tLayer.getWidth();
		height = tLayer.getHeight();
		
		tLayer.addTile(ColorRGBA.GREEN, new GrassTile());
	}
	
	public void tick(float dt){
		if(camera.getPos().x < 0) camera.getPos().x = 0;
		if(camera.getPos().x > ((width*Tile.TILE_WIDTH) - camera.getWidth()))
			camera.getPos().x = (width*Tile.TILE_WIDTH) - camera.getWidth();
		
		if(camera.getPos().y < 0) camera.getPos().y = 0;
		if(camera.getPos().y > ((height*Tile.TILE_HEIGHT) - camera.getHeight()))
			camera.getPos().y = (height*Tile.TILE_HEIGHT) - camera.getHeight();
		
		camera.setCamX(hero.getX() - (camera.getWidth()/2 - hero.getWidth()));
		camera.setCamY(hero.getY() - (camera.getHeight()/2 - hero.getHeight()));
		
		eManager.tick(dt);
		hero.tick(dt);
	}

	public void render(Graphics2D g) {
		
		xScroll = (int) (Camera.getCamX());
		yScroll = (int) (Camera.getCamY());
		tLayer.renderTileLayer(g, xScroll, yScroll);
		eManager.render(g);
		hero.render(g);
	}

}
	

