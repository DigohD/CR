package com.cr.world;

import java.awt.Graphics2D;

import com.cr.entity.enemy.Dummy;
import com.cr.game.EntityManager;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;
import com.cr.util.ColorRGBA;
import com.cr.util.Vector2f;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World{
	
	private TileLayer tLayer;
	private EntityManager eManager;
	
	private Camera camera;
	int xScroll, yScroll;
	
	private int width, height;
	Dummy dummy;
	public World(){
		eManager = new EntityManager();
	
		camera = new Camera(0, 0);
		tLayer = new TileLayer(ImageLoader.getImage("tileLayer"));
		width = tLayer.getWidth();
		height = tLayer.getHeight();
		
		dummy = new Dummy(new Vector2f(100, 500));
		
		tLayer.addTile(ColorRGBA.GREEN, new GrassTile());
	}
	
	public void tick(float dt){
		if(camera.getPos().x < 0) camera.getPos().x = 0;
		if(camera.getPos().x > ((width*Tile.TILE_WIDTH) - camera.getWidth()))
			camera.getPos().x = (width*Tile.TILE_WIDTH) - camera.getWidth();
		
		if(camera.getPos().y < 0) camera.getPos().y = 0;
		if(camera.getPos().y > ((height*Tile.TILE_HEIGHT) - camera.getHeight()))
			camera.getPos().y = (height*Tile.TILE_HEIGHT) - camera.getHeight();
		
		camera.setCamX(EntityManager.getHero().getX() - (camera.getWidth()/2 - EntityManager.getHero().getWidth()));
		camera.setCamY(EntityManager.getHero().getY() - (camera.getHeight()/2 - EntityManager.getHero().getHeight()));
		
		eManager.tick(dt);
	}

	public void render(Graphics2D g) {
		xScroll = (int) (Camera.getCamX());
		yScroll = (int) (Camera.getCamY());
		tLayer.renderTileLayer(g, xScroll, yScroll);
		eManager.render(g);
	}

}
	

