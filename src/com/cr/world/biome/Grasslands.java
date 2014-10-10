package com.cr.world.biome;

import java.awt.Point;
import java.util.ArrayList;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.util.Randomizer;
import com.cr.util.SimplexNoise;
import com.cr.world.TileLayer;
import com.cr.world.tile.DirtTile;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.SandTile;
import com.cr.world.tile.StoneTile;
import com.cr.world.tile.WaterTile;

public class Grasslands{
	
	private final float LAKE_WEIGHT = 1.8f, LAKE_LOSS = 0.05f;
	private final int LAKE_COUNT = 3;
	
	private final float DIRT_WEIGHT = 0.90f, DIRT_LOSS = 0.003f;
	private final int DIRT_COUNT = 18;
	
	private final float SAND_WEIGHT = 0.5f, SAND_LOSS = 0.00006f;
	private final int SAND_COUNT = 1000;
	
	private final float GRASS_WEIGHT = 0.9f, GRASS_LOSS = 0.010f;
	private final int GRASS_COUNT = 250;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private int width, height;
	
	private int octaves = 8;
	private float roughness = 0.4f;
	private float scale = 0.006f;
	
	private int xOffset, yOffset;
	
	public Grasslands(int width, int height){
		this.width = width;
		this.height = height;
		
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
		
//		generateLakes(width, height);
//		generateSand(width, height);
//		generateDirt(width, height);
//		generateGrass(width, height);
//		
//		fillLayers();
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
	
	private void fillLayers() {
		int bottomPixels[] = bottomLayer.getBitmap().getPixels();
		for(int i = 0; i < bottomPixels.length; i++)
			if(bottomPixels[i] == 0)
				bottomLayer.setTile(i % width, i / height, 0);
		
		int middlePixels[] = middleLayer.getBitmap().getPixels();
		for(int i = 0; i < middlePixels.length; i++)
			if(middlePixels[i] == 0 && bottomPixels[i] != ColorRGBA.BLUE)
				middleLayer.setTile(i % width, i / height, ColorRGBA.GRAY);
	}

	private void generateLakes(int worldWidth, int worldHeight){
		ArrayList<Point> centers = new ArrayList<Point>();
		
		//Create random centerpoints for lakes
		for(int i = 0; i < LAKE_COUNT; i++){
			int x = Randomizer.getInt(0, worldWidth);
			int y = Randomizer.getInt(0, worldHeight);
			Point pos = new Point(x, y);
			centers.add(pos);
//			addEntity(pos, new WaterTile(x, y));
			bottomLayer.setTile(x, y, ColorRGBA.BLUE);
		}
		
		//weight and loss defines the sizes of lakes
		float weight = LAKE_WEIGHT;
		ArrayList<Point> oldWater = new ArrayList<Point>();
		for(Point c : centers){
			boolean lakeDone = false;
			weight = LAKE_WEIGHT;
			
			oldWater.add(c);
			
			while(!lakeDone){
				ArrayList<Point> newWater = new ArrayList<Point>();
				for(Point p : oldWater){
					if(Randomizer.getFloat() < weight && p.x != 0 && !(bottomLayer.getTileID(p.x - 1, p.y) == ColorRGBA.BLUE)){
						bottomLayer.setTile(p.x - 1, p.y, ColorRGBA.BLUE);
						newWater.add(new Point(p.x-1, p.y));
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(bottomLayer.getTileID(p.x + 1, p.y) == ColorRGBA.BLUE)){
						bottomLayer.setTile(p.x + 1, p.y, ColorRGBA.BLUE);
						newWater.add(new Point(p.x+1, p.y));
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(bottomLayer.getTileID(p.x, p.y - 1) == ColorRGBA.BLUE)){
						bottomLayer.setTile(p.x, p.y - 1, ColorRGBA.BLUE);
						newWater.add(new Point(p.x, p.y-1));
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(bottomLayer.getTileID(p.x, p.y + 1) == ColorRGBA.BLUE)){
						bottomLayer.setTile(p.x, p.y + 1, ColorRGBA.BLUE);
						newWater.add(new Point(p.x, p.y+1));
					}
				}
				
				oldWater = newWater;
				weight = weight - LAKE_LOSS;
				if(weight <= 0)
					lakeDone = true;
			}
		}
	}
	
	private void generateDirt(int worldWidth, int worldHeight){
		ArrayList<Point> centers = new ArrayList<Point>();
		
		//Create random centerpoints for lakes
		for(int i = 0; i < DIRT_COUNT; i++){
			int x = Randomizer.getInt(0, worldWidth);
			int y = Randomizer.getInt(0, worldHeight);
			Point pos = new Point(x, y);
			if(bottomLayer.getTileID(x, y) != ColorRGBA.BLUE){
				middleLayer.setTile(x, y, ColorRGBA.BROWN);
				centers.add(pos);
			}else
				i--;
		}
		
		//weight and loss defines the sizes of lakes
		float weight = DIRT_WEIGHT;
		ArrayList<Point> oldDirt = new ArrayList<Point>();
		for(Point c : centers){
			boolean dirtDone = false;
			weight = DIRT_WEIGHT;
			
			oldDirt.add(c);
			
			while(!dirtDone){
				ArrayList<Point> newDirt = new ArrayList<Point>();
				for(Point p : oldDirt){
					if(Randomizer.getFloat() < weight && p.x != 0 && !(middleLayer.getTileID(p.x - 1, p.y) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileID(p.x - 1, p.y) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x - 1, p.y, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(middleLayer.getTileID(p.x + 1, p.y) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileID(p.x + 1, p.y) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x + 1, p.y, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(middleLayer.getTileID(p.x, p.y - 1) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileID(p.x, p.y - 1) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x, p.y - 1, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(middleLayer.getTileID(p.x, p.y + 1) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileID(p.x, p.y + 1) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x, p.y + 1, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x, p.y+1));
						}
					}
				}
				
				oldDirt = newDirt;
				weight = weight - DIRT_LOSS;
				if(weight <= 0)
					dirtDone = true;
			}
		}
	}
	
	private void generateSand(int worldWidth, int worldHeight){
		ArrayList<Point> centers = new ArrayList<Point>();
		
		//Create random centerpoints for lakes
		for(int i = 0; i < SAND_COUNT; i++){
			int x = Randomizer.getInt(0, worldWidth);
			int y = Randomizer.getInt(0, worldHeight);
			Point pos = new Point(x, y);
			if(bottomLayer.getTileID(x, y) != ColorRGBA.BLUE){
				middleLayer.setTile(x, y, ColorRGBA.YELLOW);
				centers.add(pos);
			}else
				i--;
		}
		
		//weight and loss defines the sizes of lakes
		float weight = SAND_WEIGHT;
		ArrayList<Point> oldSand = new ArrayList<Point>();
		for(Point c : centers){
			boolean sandDone = false;
			weight = SAND_WEIGHT;
			
			oldSand.add(c);
			
			while(!sandDone){
				ArrayList<Point> newSand = new ArrayList<Point>();
				for(Point p : oldSand){
					if(Randomizer.getFloat() < weight && p.x != 0 && !(middleLayer.getTileID(p.x - 1, p.y) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileID(p.x - 1, p.y) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x - 1, p.y, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(middleLayer.getTileID(p.x + 1, p.y) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileID(p.x + 1, p.y) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x + 1, p.y, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(middleLayer.getTileID(p.x, p.y - 1) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileID(p.x, p.y - 1) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x, p.y - 1, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(middleLayer.getTileID(p.x, p.y + 1) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileID(p.x, p.y + 1) != ColorRGBA.BLUE){
							middleLayer.setTile(p.x, p.y + 1, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x, p.y+1));
						}
					}
				}
				
				oldSand = newSand;
				weight = weight - SAND_LOSS;
				if(weight <= 0)
					sandDone = true;
			}
		}
	}
	
	private void generateGrass(int worldWidth, int worldHeight){
		ArrayList<Point> centers = new ArrayList<Point>();
		
		//Create random centerpoints for lakes
		for(int i = 0; i < GRASS_COUNT; i++){
			int x = Randomizer.getInt(0, worldWidth);
			int y = Randomizer.getInt(0, worldHeight);
			Point pos = new Point(x, y);
			if(middleLayer.getTileID(x, y) == ColorRGBA.BROWN){
				topLayer.setTile(x, y, ColorRGBA.GREEN);
				centers.add(pos);
			}else
				i--;
		}
		
		//weight and loss defines the sizes of lakes
		float weight = GRASS_WEIGHT;
		ArrayList<Point> oldGrass = new ArrayList<Point>();
		for(Point c : centers){
			boolean grassDone = false;
			weight = GRASS_WEIGHT;
			
			oldGrass.add(c);
			
			while(!grassDone){
				ArrayList<Point> newGrass = new ArrayList<Point>();
				for(Point p : oldGrass){
					if(Randomizer.getFloat() < weight && p.x != 0 && !(topLayer.getTileID(p.x - 1, p.y) == ColorRGBA.GREEN)){
						if(middleLayer.getTileID(p.x - 1, p.y) == ColorRGBA.BROWN){
							topLayer.setTile(p.x - 1, p.y, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(topLayer.getTileID(p.x + 1, p.y) == ColorRGBA.GREEN)){
						if(middleLayer.getTileID(p.x + 1, p.y) == ColorRGBA.BROWN){
							topLayer.setTile(p.x + 1, p.y, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(topLayer.getTileID(p.x, p.y - 1) == ColorRGBA.GREEN)){
						if(middleLayer.getTileID(p.x, p.y - 1) == ColorRGBA.BROWN){
							topLayer.setTile(p.x, p.y - 1, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(topLayer.getTileID(p.x, p.y + 1) == ColorRGBA.GREEN)){
						if(middleLayer.getTileID(p.x, p.y + 1) == ColorRGBA.BROWN){
							topLayer.setTile(p.x, p.y + 1, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x, p.y+1));
						}
					}
				}
				
				oldGrass = newGrass;
				weight = weight - GRASS_LOSS;
				if(weight <= 0)
					grassDone = true;
			}
		}
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
}
