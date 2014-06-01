package com.cr.world.biome;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import com.cr.util.ColorRGBA;
import com.cr.util.Randomizer;
import com.cr.world.TileLayer;
import com.cr.world.tile.DirtTile;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.SandTile;
import com.cr.world.tile.StoneTile;
import com.cr.world.tile.WaterTile;

public class Grasslands{

	private final float LAKE_WEIGHT = 0.60f, LAKE_LOSS = 0.005f;
	private final int LAKE_COUNT = 9;
	
	private final float DIRT_WEIGHT = 0.80f, DIRT_LOSS = 0.006f;
	private final int DIRT_COUNT = 9;
	
	private final float SAND_WEIGHT = 0.5f, SAND_LOSS = 0.00006f;
	private final int SAND_COUNT = 1000;
	
	private final float GRASS_WEIGHT = 0.7f, GRASS_LOSS = 0.006f;
	private final int GRASS_COUNT = 20;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	public Grasslands(){
		bottomLayer = new TileLayer(100, 100);
		middleLayer = new TileLayer(100, 100);
		topLayer = new TileLayer(100, 100);
		
		bottomLayer.addTileType(ColorRGBA.BLUE, new WaterTile());
		bottomLayer.addTileType(ColorRGBA.GRAY, new StoneTile());
		
		middleLayer.addTileType(ColorRGBA.BROWN, new DirtTile());
		middleLayer.addTileType(ColorRGBA.YELLOW, new SandTile());
		middleLayer.addTileType(ColorRGBA.GRAY, new StoneTile());
		
		topLayer.addTileType(ColorRGBA.GREEN, new GrassTile());
		
		generateLakes(100, 100);
		
		generateSand(100, 100);
		generateDirt(100, 100);
		
		generateGrass(100, 100);
		
		fillLayers();
	}
	
	private void fillLayers() {
		int bottomPixels[] = bottomLayer.getPixels();
		for(int i = 0; i < bottomPixels.length; i++)
			if(bottomPixels[i] == 0)
				bottomLayer.addTile(i % 100, i / 100, ColorRGBA.GRAY);
		
		int middlePixels[] = middleLayer.getPixels();
		for(int i = 0; i < middlePixels.length; i++)
			if(middlePixels[i] == 0 && bottomPixels[i] != ColorRGBA.BLUE)
				middleLayer.addTile(i % 100, i / 100, ColorRGBA.GRAY);
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
			bottomLayer.addTile(x, y, ColorRGBA.BLUE);
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
					if(Randomizer.getFloat() < weight && p.x != 0 && !(bottomLayer.getTileColor(p.x - 1, p.y) == ColorRGBA.BLUE)){
						bottomLayer.addTile(p.x - 1, p.y, ColorRGBA.BLUE);
						newWater.add(new Point(p.x-1, p.y));
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(bottomLayer.getTileColor(p.x + 1, p.y) == ColorRGBA.BLUE)){
						bottomLayer.addTile(p.x + 1, p.y, ColorRGBA.BLUE);
						newWater.add(new Point(p.x+1, p.y));
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(bottomLayer.getTileColor(p.x, p.y - 1) == ColorRGBA.BLUE)){
						bottomLayer.addTile(p.x, p.y - 1, ColorRGBA.BLUE);
						newWater.add(new Point(p.x, p.y-1));
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(bottomLayer.getTileColor(p.x, p.y + 1) == ColorRGBA.BLUE)){
						bottomLayer.addTile(p.x, p.y + 1, ColorRGBA.BLUE);
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
			if(bottomLayer.getTileColor(x, y) != ColorRGBA.BLUE){
				middleLayer.addTile(x, y, ColorRGBA.BROWN);
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
					if(Randomizer.getFloat() < weight && p.x != 0 && !(middleLayer.getTileColor(p.x - 1, p.y) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileColor(p.x - 1, p.y) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x - 1, p.y, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(middleLayer.getTileColor(p.x + 1, p.y) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileColor(p.x + 1, p.y) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x + 1, p.y, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(middleLayer.getTileColor(p.x, p.y - 1) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileColor(p.x, p.y - 1) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x, p.y - 1, ColorRGBA.BROWN);
							newDirt.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(middleLayer.getTileColor(p.x, p.y + 1) == ColorRGBA.BROWN)){
						if(bottomLayer.getTileColor(p.x, p.y + 1) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x, p.y + 1, ColorRGBA.BROWN);
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
			if(bottomLayer.getTileColor(x, y) != ColorRGBA.BLUE){
				middleLayer.addTile(x, y, ColorRGBA.YELLOW);
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
					if(Randomizer.getFloat() < weight && p.x != 0 && !(middleLayer.getTileColor(p.x - 1, p.y) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileColor(p.x - 1, p.y) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x - 1, p.y, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(middleLayer.getTileColor(p.x + 1, p.y) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileColor(p.x + 1, p.y) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x + 1, p.y, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(middleLayer.getTileColor(p.x, p.y - 1) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileColor(p.x, p.y - 1) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x, p.y - 1, ColorRGBA.YELLOW);
							newSand.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(middleLayer.getTileColor(p.x, p.y + 1) == ColorRGBA.YELLOW)){
						if(bottomLayer.getTileColor(p.x, p.y + 1) != ColorRGBA.BLUE){
							middleLayer.addTile(p.x, p.y + 1, ColorRGBA.YELLOW);
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
			if(middleLayer.getTileColor(x, y) == ColorRGBA.BROWN){
				topLayer.addTile(x, y, ColorRGBA.GREEN);
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
					if(Randomizer.getFloat() < weight && p.x != 0 && !(topLayer.getTileColor(p.x - 1, p.y) == ColorRGBA.GREEN)){
						if(middleLayer.getTileColor(p.x - 1, p.y) == ColorRGBA.BROWN){
							topLayer.addTile(p.x - 1, p.y, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x-1, p.y));
						}
					}
					if(Randomizer.getFloat() < weight  && p.x != worldWidth-1 && !(topLayer.getTileColor(p.x + 1, p.y) == ColorRGBA.GREEN)){
						if(middleLayer.getTileColor(p.x + 1, p.y) == ColorRGBA.BROWN){
							topLayer.addTile(p.x + 1, p.y, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x+1, p.y));
						}
					}	
					if(Randomizer.getFloat() < weight  && p.y != 0 && !(topLayer.getTileColor(p.x, p.y - 1) == ColorRGBA.GREEN)){
						if(middleLayer.getTileColor(p.x, p.y - 1) == ColorRGBA.BROWN){
							topLayer.addTile(p.x, p.y - 1, ColorRGBA.GREEN);
							newGrass.add(new Point(p.x, p.y-1));
						}
					}
					if(Randomizer.getFloat() < weight  && p.y != worldHeight-1 && !(topLayer.getTileColor(p.x, p.y + 1) == ColorRGBA.GREEN)){
						if(middleLayer.getTileColor(p.x, p.y + 1) == ColorRGBA.BROWN){
							topLayer.addTile(p.x, p.y + 1, ColorRGBA.GREEN);
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
