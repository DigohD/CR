package com.cr.world;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.shader.Shader;
import com.cr.game.Game;
import com.cr.world.biome.Grasslands;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private static Transform transform;
	
	private static Shader waterShader;
	
	private float amplitudeWave = 2f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.3f;
	public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private float time = 1f;
	private float dayNightCycleTime = 100.0f;
	private float targetTimeMax = 2f;
	private float targetTimeMin = 0.2f;
	
	private boolean day = true, night = false;
	Texture mask = new Texture("ripple");
	Mesh m;
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		waterShader = new Shader("waterVertShader", "waterFragShader");
		waterShader.addUniform("transformation");
		waterShader.addUniform("waveDataX");
		waterShader.addUniform("waveDataY");
		waterShader.addUniform("time");
		waterShader.addUniform("sampler");
		waterShader.addUniform("sampler2");
		waterShader.setUniformi("sampler", 0);
		waterShader.setUniformi("sampler2", 1);
		
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0), new Vector2f(0,0)), 
				 new Vertex(new Vector3f(0, 0 + mask.getHeight(), 0), new Vector2f(0,1)),
				 new Vertex(new Vector3f(0 + mask.getWidth() , 0 + mask.getHeight(), 0), new Vector2f(1,1)),
				 new Vertex(new Vector3f(0 + mask.getWidth(), 0, 0), new Vector2f(1,0))};

		int[] indices = {0,1,2, 
			 2,3,0};
		
		m = new Mesh(vertices, indices);
		
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
	
	public void tick(float dt){
		if(time <= 2.0f && day){
			time += targetTimeMax / dayNightCycleTime * dt;
			if(time > 2.0f) {
				night = true;
				day = false;
			}
		}
		
		if(night){
			time -= targetTimeMax / dayNightCycleTime * dt;
			if(time <= 0.2f){
				day = true;
				night = false;
			}
		}
	}
	
	public void renderMap(Screen screen){
		angleWave += Game.dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
		
		

		waterShader.bind();
		glActiveTexture(GL_TEXTURE1);
		mask.bind();
		m.render();
		mask.unbind();
		glActiveTexture(GL_TEXTURE0);
		waterShader.setUniformf("time", time);
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
	

	public static Shader getWaterShader(){
		return waterShader;
	}


}
