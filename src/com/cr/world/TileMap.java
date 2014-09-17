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
	
	private float					amplitudeWave = 2f;
	private float					angleWave = 2.86f;
	private float					angleWaveSpeed = 0.3f;
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
	
	public void renderMap(Screen screen, int xp, int yp){
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
