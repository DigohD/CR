package com.cr.states;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.util.BiomeGen;
import com.cr.util.Randomizer;
import com.cr.util.SimplexNoise;
import com.cr.util.ColorToHeightPair;

public class BiomeTestState extends GameState{
	
	private Mesh mesh;
	private Transform t;
	private Texture texture;
	private BufferedImage image;
	private int[] pixels;
	
	private int width = Window.getWidth() / 2;
	private int height = Window.getHeight() / 2;
	
	private int octaves = 8;
	private float roughness = 0.3f;
	private float layerFrequency = 0.009f;
	private float layerWeight = 1.0f;

	public BiomeTestState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		t = new Transform();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
			
		List<ColorToHeightPair> list = new ArrayList<ColorToHeightPair> ();
		
		list.add(new ColorToHeightPair(ColorRGBA.WHITE, -0.9f));
		list.add(new ColorToHeightPair(ColorRGBA.GRAY, -0.5f));
		list.add(new ColorToHeightPair(ColorRGBA.DARK_GREEN, 0.0f));
		list.add(new ColorToHeightPair(ColorRGBA.GREEN, 0.4f));
		list.add(new ColorToHeightPair(ColorRGBA.YELLOW, 0.5f));
		
		BiomeGen.generateProceduralTerrain(list, pixels, width, height, octaves, roughness, layerFrequency, layerWeight, true, ColorRGBA.BLUE);
		
		texture = new Texture(image);
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0), new Vector2f(0,0)), 
							 new Vertex(new Vector3f(0, 0 + height, 0), new Vector2f(0,1)),
							 new Vertex(new Vector3f(0 + width , 0 + height, 0), new Vector2f(1,1)),
							 new Vertex(new Vector3f(0 + width, 0, 0), new Vector2f(1,0))};

		int[] indices = {0,1,2, 
						 2,3,0};
		
		mesh = new Mesh(vertices, indices);
		t.translate(0, 0 , 0);
		t.scale(1, 1, 0);
	}
	
	public void generateMap(int octaves, float roughness, float scale){
		float[] simplexNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x+y*width];
				if(height < -0.9f) pixels[x+y*width] = ColorRGBA.WHITE;
				else if(height < -0.5f) pixels[x+y*width] = ColorRGBA.GRAY;
				else if(height < 0.0f) pixels[x+y*width] = ColorRGBA.DARK_GREEN;
				else if(height < 0.4f) pixels[x+y*width] = ColorRGBA.GREEN;
				else if(height < 0.5f) pixels[x+y*width] = ColorRGBA.YELLOW;
				else pixels[x+y*width] = ColorRGBA.BLUE;
			}
		}
		
	}
	
	public float[] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float scale){
	      float[] totalNoise = new float[width*height];
	      float layerFrequency = scale;
	      float layerWeight = 1f;
	      float weightSum = 0;

	      for(int octave = 0; octave < octaves; octave++) {
	      	int xO = Randomizer.getInt(0, 16000000);
	      	int yO = Randomizer.getInt(0, 16000000);
	      
	        for(int x = 0; x < width; x++) {
	        	for(int y = 0; y < height; y++) {
		            totalNoise[x+y*width] += (float) SimplexNoise.noise((x + xO) * layerFrequency,
		            		(y + yO) * layerFrequency) * layerWeight;
		        }
	        }
	         
	          layerFrequency *= 2;
	          weightSum += layerWeight;
	          layerWeight *= roughness;
	      }
	      
	      return totalNoise;
	}

	
	
	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.ESCAPE))
			gsm.pop();
		
		if(Input.getKey(Input.ENTER))init();
		
		
	}

	@Override
	public void render(Screen screen){
		Game.shader.bind();
		Game.shader.setUniform("transformation", t.getOrthoTransformation());
		texture.bind();
		mesh.render();
		texture.unbind();
		Game.shader.unbind();
	}

}
