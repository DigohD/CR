package com.cr.world;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.world.biome.Grasslands;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private static Transform transform;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		transform = new Transform();
		
		Grasslands g = new Grasslands(width, height);
		
		bottomLayer = g.getBottomLayer();
		middleLayer = g.getMiddleLayer();
		topLayer = g.getTopLayer();
		
		bottomLayer.generateTileLayer(true);
		middleLayer.generateTileLayer(false);
		topLayer.generateTileLayer(false);
	}
	
	public TileMap(){
		transform = new Transform();
	}
	
	public void tick(int xp, int yp){
//		int x0 = xp / 58;
//		int x1 = (xp + Window.getWidth()+58) / 58;
//		int y0 = yp / 38;
//		int y1 = (yp + Window.getHeight()+38) / 38;
		
//		int x0 = xp / 58 - 5;
//		int x1 = (xp + Window.getWidth()+58*4) / 58;
//		int y0 = yp / 38 - 7;
//		int y1 = (yp + Window.getHeight()+38*5) / 38;
//		
//		
		
//		for(int y = y0; y < y1; y++){
//			for(int x = x0; x < x1; x++){
//				if(bottomLayer.tileExists(x, y)){
//					if(bottomLayer.getTile(x, y) instanceof WaterTile){
//						WaterTile tile = (WaterTile) bottomLayer.getTile(ColorRGBA.BLUE);
//						tile.tick(bottomLayer, x + y * width);
//					}
//				}
//				
//			
//			}
//		}
		
		WaterTile tile = (WaterTile) bottomLayer.getTile(ColorRGBA.BLUE);
		tile.tick(bottomLayer, 0);
		
		
//		for(int i = 0; i < width*height; i++){
//			WaterTile tile = (WaterTile) bottomLayer.getTile(ColorRGBA.BLUE);
//			tile.tick(bottomLayer, i);
//		}
	
		
		//bottomLayer.getMesh().updateTexCoordData(texCoords)
					
							
	}
	
	public void renderMap(Screen screen, int xp, int yp){
		int x0 = xp / 58 - 5;
		int x1 = (xp + Window.getWidth()+58*4) / 58;
		int y0 = yp / 38 - 7;
		int y1 = (yp + Window.getHeight()+38*5) / 38;
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				if(bottomLayer.tileExists(x, y)){
					if(bottomLayer.getTile(x, y) instanceof WaterTile){
						WaterTile tile = (WaterTile) bottomLayer.getTile(x, y);	
						tile.render(screen, x, y, xp, yp);
					}
				}
//		bottomLayer.renderTileLayer();
		middleLayer.renderTileLayer();
		topLayer.renderTileLayer();
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
	
	public static Transform getTransform() {
		return transform;
	}


}
