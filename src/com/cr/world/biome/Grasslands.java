package com.cr.world.biome;

import java.util.ArrayList;
import java.util.List;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.util.Randomizer;
import com.cr.util.SimplexNoise;
import com.cr.world.TileLayer;
import com.cr.world.tile.DirtTile;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class Grasslands{
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private int width, height;
	
	private int octaves = 8;
	private float roughness = 0.4f;
	private float scale = 0.006f;
	
	private int xOffset, yOffset;
	
	private List<Vector2f> treePositions;
	
	public Grasslands(int width, int height){
		this.width = width;
		this.height = height;
		
		treePositions = new ArrayList<Vector2f>();
		
		bottomLayer = new TileLayer(width, height, 0);
		middleLayer = new TileLayer(width, height, 0);
		topLayer    = new TileLayer(width, height, -0.6f);
		
		bottomLayer.addTileType(ColorRGBA.BLUE, new WaterTile());
		middleLayer.addTileType(ColorRGBA.BROWN, new DirtTile());
//		middleLayer.addTileType(ColorRGBA.YELLOW, new SandTile());
//		middleLayer.addTileType(ColorRGBA.GRAY, new StoneTile());
		
		topLayer.addTileType(ColorRGBA.GREEN, new GrassTile());
		
		xOffset = Randomizer.getInt(0, 16000000);
      	yOffset = Randomizer.getInt(0, 16000000);
		
		generateBottomLayer(octaves, roughness, scale);
		generateMiddleLayer(octaves, roughness, scale);
		generateTopLayer(octaves, roughness, scale);
		
		generateTrees(octaves, roughness, scale);
	}
	
	public void generateTrees(int octaves, float roughness, float scale){
		float[] simplexNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x+y*width];
				if(height > 0 && topLayer.getBitmap().getPixel(x, y) == ColorRGBA.GREEN){
					if(Randomizer.getInt(0, 10) == 0)
						treePositions.add(new Vector2f(x * Tile.getTileWidth(),y*Tile.getTileHeight()));
				}
			}
		}
		
	}
	
	public void generateBottomLayer(int octaves, float roughness, float scale){
		float[] simplexNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x+y*width];
				if(height < -0.5) bottomLayer.getBitmap().setPixel(x, y, ColorRGBA.BLUE);
				else bottomLayer.getBitmap().setPixel(x, y, ColorRGBA.BLACK);
			}
		}
		
	}
	
	public void generateMiddleLayer(int octaves, float roughness, float scale){
		float[] simplexNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x+y*width];
				if(height < -0.4 && height >= -0.5f) middleLayer.getBitmap().setPixel(x, y, ColorRGBA.BROWN);
				else middleLayer.getBitmap().setPixel(x, y, ColorRGBA.BLACK);
			}
		}
		
	}
	
	
	public void generateTopLayer(int octaves, float roughness, float scale){
		float[] simplexNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				float height = simplexNoise[x+y*width];
				if(height >= -0.4) topLayer.getBitmap().setPixel(x, y, ColorRGBA.GREEN);
				else topLayer.getBitmap().setPixel(x, y, ColorRGBA.BLACK);
			}
		}
		
	}
	
	public float[] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float scale){
	      float[] totalNoise = new float[width*height];
	      float layerFrequency = scale;
	      float layerWeight = 1;
	      float weightSum = 0;

	      for(int octave = 0; octave < octaves; octave++) {
	         //Calculate single layer/octave of simplex noise, then add it to total noise
	         for(int x = 0; x < width; x++)
	            for(int y = 0; y < height; y++)
	            	totalNoise[x+y*width] += (float) SimplexNoise.noise((x + xOffset) * layerFrequency,
            				(y + yOffset) * layerFrequency) * layerWeight;
	   
	         //Increase variables with each incrementing octave
	          layerFrequency *= 2;
	          weightSum += layerWeight;
	          layerWeight *= roughness;
	           
	      }
	      
	      return totalNoise;
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

	public List<Vector2f> getTreePositions() {
		return treePositions;
	}
}
