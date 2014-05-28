package com.cr.world;

import java.awt.Graphics2D;

import com.cr.entity.enemy.Dummy;
import com.cr.game.EntityManager;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;
import com.cr.util.ColorRGBA;
import com.cr.util.Randomizer;
import com.cr.util.Vector2f;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.PoisonTile;
import com.cr.world.tile.Tile;

public class World{
	
	private TileLayer tLayer, tLayer2;
	private EntityManager eManager;
	
	private Camera camera;
	int xScroll, yScroll;
	
	private int width, height;
	Dummy dummy;
	
	public World(){
		eManager = new EntityManager();
	
		camera = new Camera(0, 0);
//		tLayer = new TileLayer(ImageLoader.getImage("tileLayer"));
		tLayer = new TileLayer(30, 30);
		tLayer2 = new TileLayer(100, 100);
		
		width = tLayer.getWidth();
		height = tLayer.getHeight();
		
		dummy = new Dummy(new Vector2f(100, 500));
		
	
		tLayer.addTile(ColorRGBA.RED, new PoisonTile());
		tLayer.addTile(ColorRGBA.GREEN, new GrassTile());
		
		tLayer.createTileStack(ColorRGBA.RED, ColorRGBA.GREEN);
		
		//tLayer.generateRandomLayer();
		tLayer.generateTileLayer();
		//tLayer2.generateTileLayer();
		
		tLayer.removeTile(10, 10);
		
	}
	
	int timer = 0;
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(camera.getPos().x < 0) camera.getPos().x = 0;
		if(camera.getPos().x > ((width*Tile.TILE_WIDTH) - camera.getWidth()))
			camera.getPos().x = (width*Tile.TILE_WIDTH) - camera.getWidth();
		
		if(camera.getPos().y < 0) camera.getPos().y = 0;
		if(camera.getPos().y > ((height*Tile.TILE_HEIGHT) - camera.getHeight()))
			camera.getPos().y = (height*Tile.TILE_HEIGHT) - camera.getHeight();
		
		camera.setCamX(EntityManager.getHero().getX() - (camera.getWidth()/2 - EntityManager.getHero().getWidth()));
		camera.setCamY(EntityManager.getHero().getY() - (camera.getHeight()/2 - EntityManager.getHero().getHeight()));
		
//		if(timer % 40 == 0){
//			new Dummy(new Vector2f(Randomizer.getFloat(50, width * Tile.TILE_WIDTH), Randomizer.getFloat(50, height * Tile.TILE_HEIGHT)));
//		}

		eManager.tick(dt);
	}

	public void render(Graphics2D g) {
		xScroll = (int) (Camera.getCamX());
		yScroll = (int) (Camera.getCamY());
		
		//tLayer2.renderTileLayer(g, xScroll, yScroll);
		tLayer.renderTileLayer(g, xScroll, yScroll);
	
		eManager.render(g);
	}
}
	

