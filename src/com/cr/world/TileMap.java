package com.cr.world;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.game.Game;
import com.cr.world.biome.Grasslands;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private static Transform transform;
	
	private Shader waterShader;
	
	private float					amplitudeWave = 0.1f;
	private float					angleWave = 0.0f;
	private float					angleWaveSpeed = 1.0f;
	public static final float 		PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		waterShader = new Shader("waterVertShader", "waterFragShader");
		waterShader.addUniform("transformation");
		waterShader.addUniform("waveDataX");
		waterShader.addUniform("waveDataY");
		waterShader.addUniform("sampler");
		waterShader.setUniformi("sampler", 0);
		
		transform = new Transform();
		
		Grasslands g = new Grasslands(width, height);
		
		bottomLayer = g.getBottomLayer();
		middleLayer = g.getMiddleLayer();
		topLayer = g.getTopLayer();
		
		bottomLayer.generateTileLayer();
		middleLayer.generateTileLayer();
		topLayer.generateTileLayer();
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
		
//		WaterTile tile = (WaterTile) bottomLayer.getTile(ColorRGBA.BLUE);
//		tile.tick(bottomLayer, 0);
		
		
//		for(int i = 0; i < width*height; i++){
//			WaterTile tile = (WaterTile) bottomLayer.getTile(ColorRGBA.BLUE);
//			tile.tick(bottomLayer, i);
//		}
	
		
		//bottomLayer.getMesh().updateTexCoordData(texCoords)
					
							
	}
	
	public void renderMap(Screen screen, int xp, int yp){
//		int x0 = xp / 58 - 5;
//		int x1 = (xp + Window.getWidth()+58*4) / 58;
//		int y0 = yp / 38 - 7;
//		int y1 = (yp + Window.getHeight()+38*6) / 38;
//		
//		for(int y = y0; y < y1; y++)
//			for(int x = x0; x < x1; x++)
//				if(bottomLayer.tileExists(x, y)){
//					if(bottomLayer.getTile(x, y) instanceof WaterTile){
//						WaterTile tile = (WaterTile) bottomLayer.getTile(x, y);	
//						tile.render(screen, x, y, xp, yp);
//					}
//				}
		
		angleWave += Game.dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
		
		waterShader.bind();
		waterShader.setUniformf("waveDataX", angleWave);
		waterShader.setUniformf("waveDataY", amplitudeWave);
		waterShader.setUniform("transformation", transform.getOrthoTransformation());
		bottomLayer.renderTileLayer(true);
		waterShader.unbind();
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
	
	public static Transform getTransform() {
		return transform;
	}


}
