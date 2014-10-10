package com.cr.states;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Texture;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.util.Randomizer;
import com.cr.util.SimplexNoise;

public class BiomeTestState extends GameState{
	
	private Mesh mesh;
	private Transform t;
	private Texture texture;
	private BufferedImage image;
	private int[] pixels;
	
	private int width = 500, height = 500;
	
	private float persistence = 0.001f;
	private int octaves = 12;
	
	private SimplexNoise noise = new SimplexNoise();

	public BiomeTestState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		t = new Transform();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
		
		genMap();
		//generateOctavedSimplexNoise(5.0f);
		
		texture = new Texture(image);
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0), new Vector2f(0,0)), 
							 new Vertex(new Vector3f(0, 0 + height, 0), new Vector2f(0,1)),
							 new Vertex(new Vector3f(0 + width , 0 + height, 0), new Vector2f(1,1)),
							 new Vertex(new Vector3f(0 + width, 0, 0), new Vector2f(1,0))};

		int[] indices = {0,1,2, 
						 2,3,0};
		
		mesh = new Mesh(vertices, indices);
		t.translate(0, 0 , 0);
		t.scale(2, 2, 0);
	}
	
	public void genMap(){
		float[][] simplexNoise = generateOctavedSimplexNoise(width, height, 3, 0.4f, 0.005f);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x][y];
				if(height < 0.5) pixels[x+y*width] = ColorRGBA.BLUE;
				else pixels[x+y*width] = ColorRGBA.GREEN;
			}
		}
		
	}
	
	public float[][] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float scale){
	      float[][] totalNoise = new float[width][height];
	       float layerFrequency = scale;
	       float layerWeight = 1;
	       float weightSum = 0;

	       for (int octave = 0; octave < octaves; octave++) {
	          //Calculate single layer/octave of simplex noise, then add it to total noise
	          for(int x = 0; x < width; x++){
	             for(int y = 0; y < height; y++){
	                totalNoise[x][y] += (float) SimplexNoise.noise(x * layerFrequency,y * layerFrequency) * layerWeight;
	             }
	          }
	          
	          //Increase variables with each incrementing octave
	           layerFrequency *= 2;
	           weightSum += layerWeight;
	           layerWeight *= roughness;
	           
	       }
	       return totalNoise;
	   }
	
//	private void generatePerlinNoise(float frequency){
//		float f = frequency / (float) width;
//		for(int y = 0; y < height; y++){
//			for(int x = 0; x < width; x++){
//				float xOffset = (x * f) + (Randomizer.getFloat() % 1);
//				float yOffset = (y * f) + (Randomizer.getFloat() % 1);
//				double height = (SimplexNoise.noise((x * f), (y * f)) + 1) / 2;
//				
//				//System.out.println(height);
//				
//				if(height < 0.5) pixels[x+y*width] = ColorRGBA.BLUE;
//				else pixels[x+y*width] = ColorRGBA.GREEN;
//				
//			
//			}
//		}
//	}
	
	public float noise(float x, float y, float frequency){
		return SimplexNoise.noise(x / frequency, y / frequency);
	}
	
	public float octavedNoise(float x, float y, int octaves, float roughness, float scale) {
	    float noiseSum = 0;
	    float layerFrequency = scale;
	    float layerWeight = 1;
	    float weightSum = 0;

	    for (int octave = 0; octave < octaves; octave++) {
	        noiseSum += noise(x * layerFrequency, y * layerFrequency) * layerWeight;
	        layerFrequency *= 2;
	        weightSum += layerWeight;
	        layerWeight *= roughness;
	    }
	    return noiseSum / weightSum;
	}

	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.ESCAPE))
			gsm.pop();
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
	
//	private float[] generateWhiteNoise(int width, int height){
//		Random r = new Random(0);
//		float[] noise = new float[width*height];
//		
//		for(int y = 0; y < height; y++)
//			for(int x = 0; x < width; x++)
//				noise[x + (y*width)] = r.nextFloat() % 1;
//			
//		return noise;
//	}
//	
//	private float[] generateSmoothNoise(float[] baseNoise, int octave){
//		float[] smoothNoise = new float[width*height];
//		
//		int samplerPeriod = 1 << octave;
//		float samplerFrequency = 1.0f / samplerPeriod;
//		
//		
//		
//	}
	
	private float interpolate(float a, float b, float x){
		float ft = x * (float)Math.PI;
		float f = (1.0f - (float)Math.cos(ft)) * 5.0f;
		
		return a * (1.0f - f) + (b*f);
	}
	
//	private float noise(int x, int y){
//		int n = x + (y*57);
//		n = (int) Math.pow((n << 13), n); 
//		return (float) (1.0f - ( (n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0); 
//	}
	Random r = new Random(2);
	private float noise(float x, float y){
		
		return (r.nextFloat() % 1); 
	}
	
	private float smoothNoise(float x, float y){
		float corners = (noise((x-1.0f), (y-1.0f)) + noise((x+1.0f), (y-1.0f)) 
				+ noise((x-1.0f), (y+1.0f)) + noise((x+1.0f), (y+1.0f))) / 16.0f;
		
		float sides = (noise((x-1.0f), (y)) + noise((x+1.0f), (y)) 
				+ noise((x), (y-1.0f)) + noise((x), (y+1.0f))) / 8.0f;
		
		float center = noise((x), (y)) / 4.0f;
		
		return (corners + sides + center);
	}
	
	private float interpolatedNoise(float x, float y){
//		int integer_X = (int) x;
//		float fractional_X = x - integer_X;
//		
//		int integer_Y = (int) y;
//		float fractional_Y = y - integer_Y;
		
		float v1 = smoothNoise(x, y);
		float v2 = smoothNoise(x + 1.0f, y);
		float v3 = smoothNoise(x, y + 1.0f);
		float v4 = smoothNoise(x + 1.0f, y + 1.0f);
		
		float i1 = interpolate(v1, v2, x);
		float i2 = interpolate(v3, v4, x);
		
		return interpolate(i1, i2, y);
	}
	
	private float perlinNoise2D(float x, float y){
		float total = 0;
		float p = persistence;
		int n = octaves - 1;
		
		for(int i = 0; i < n; i++){
			float frequency = (float) Math.pow(2, i);
			float amplitude = (float) Math.pow(p, i);
			total += interpolatedNoise(x*frequency, y*frequency) * amplitude;
		}
		
		return total;
	}

}
