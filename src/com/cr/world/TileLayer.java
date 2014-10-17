package com.cr.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.Bitmap;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.shader.Shader;
import com.cr.world.tile.Tile;
import com.cr.world.tile.WaterTile;

public class TileLayer {
	
	private static int width, height;
	private float depth;

	private Bitmap bitmap;
	private Shader shader;
	private Transform transform;
	
	private List<Mesh> meshes; 
	private HashMap<Integer, Tile> tiles;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	private float scaleFactor = 1f;
	private static float counter;
	
	int[] pixelData;
	
	public TileLayer(LinkedList<Integer> pixels, int width, int height, float depth){
		this.transform = World.getTransform();
		this.shader = World.getShader();
		meshes = new ArrayList<Mesh>();
		tiles = new HashMap<Integer, Tile>();
		
		TileLayer.width = width;
		TileLayer.height = height;
		
		counter = width*height*0.0001f*3;
		
		int size = width*height;
		
		int[] pixelData = new int[size];
		
		for(int i = size-1; i >=0; i--){
			pixelData[i] = pixels.getFirst();
			pixels.removeFirst();
		}
		
		bitmap = new Bitmap(width, height);
		bitmap.setPixels(pixelData);
	}
	
	public TileLayer(int width, int height, float depth){
		this.depth = depth;
		bitmap = new Bitmap(width, height);
		
		TileLayer.width = width;
		TileLayer.height = height;
		
		counter = width*height*0.0001f*3;
		
		this.transform = World.getTransform();
		
		this.shader = World.getShader();
		
		meshes = new ArrayList<Mesh>();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public TileLayer(String name, Transform transform){
		this.transform = transform;
		bitmap = new Bitmap(name);
		
		TileLayer.width = bitmap.getWidth();
		TileLayer.height = bitmap.getHeight();
		this.transform = transform;
		this.shader = World.getShader();
		
		meshes = new ArrayList<Mesh>();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public void generateTileLayer(){
		generateTileLayer(0, 0, width/2, height/2);
		generateTileLayer(width/2, 0, width, height/2);
		generateTileLayer(0, height/2, width/2, height);
		generateTileLayer(width/2, height/2, width, height);
	}
	
	public void generateTileLayer(int xStart, int yStart, int xEnd, int yEnd){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float tWidth = Tile.getTileWidth();
		float tHeight = Tile.getTileHeight();
		
		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				if(bitmap.getPixel(x, y) == 0) continue;

				calcTexCoords(tiles.get(bitmap.getPixel(x, y)).getRow(), tiles.get(bitmap.getPixel(x, y)).getCol());
	
				float xPos = x * tWidth;
				float yPos = y * tHeight;
				
				float xOffset = 7f;
				float yOffset = 5f;
	
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 0);
				
				vertices.add(new Vertex(new Vector3f(xPos, yPos, counter -= 0.0001f), new Vector2f(xLow, yLow)));
				vertices.add(new Vertex(new Vector3f(xPos, yPos + tHeight + yOffset, counter -= 0.0001f), new Vector2f(xLow, yHigh)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos + tHeight + yOffset, counter -= 0.0001f), new Vector2f(xHigh, yHigh)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos, counter -= 0.0001f), new Vector2f(xHigh, yLow)));
			}
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
	
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
	
		meshes.add(new Mesh(vertexArray, iArray));
		transform.scale(scaleFactor, scaleFactor, 1);
	}
	
	public void renderTileLayer(boolean water){
		transform.translate(0, 0, depth);
		
		for(Integer i : tiles.keySet()){
			shader.setUniformf("material_shininess", tiles.get(i).getMaterial().getMaterialShininess());
			shader.setUniformf("material_diffuse_color", tiles.get(i).getMaterial().getDiffuseColor());
			shader.setUniformf("material_specular_color", tiles.get(i).getMaterial().getSpecularColor());
			shader.setUniformf("material_emissive_color", tiles.get(i).getMaterial().getEmissiveColor());
		}
	
		if(water){
			shader.setUniformf("isWater", 1.0f);
		}else shader.setUniformf("isWater", 0.0f);
		
		for(Mesh m : meshes)
			m.render();
	
	}
	
	private void calcTexCoords(float row, float col){
		xLow = col / Tile.TILE_ATLAS_COLS;
		xHigh = xLow + (1 / Tile.TILE_ATLAS_COLS);
		yLow = row / Tile.TILE_ATLAS_ROWS;
		yHigh = yLow + (1 / Tile.TILE_ATLAS_ROWS);
	}
	
	public void addTileType(int color, Tile tile){
		tiles.put(color, tile);
	}
	
	public void setTile(int x, int y, int color){
		bitmap.setPixel(x, y, color);
	}
	
	public void removeTile(int x, int y){
		bitmap.setPixel(x, y, 0);
	}
	
	public int getTileID(){
		int col = 0;
		for(Integer i : tiles.keySet())
			col = i;
		return col;
	}
	
	public int getTileID(int x, int y){
		return bitmap.getPixel(x, y);
	}
	
	public Tile getTile(int x, int y){
		return tiles.get(bitmap.getPixel(x, y));
	}
	
	public Tile getTile(int color){
		return tiles.get(color);
	}
	
	public boolean tileExists(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if(tiles.containsKey(bitmap.getPixel(x, y)))
			return true;
		return false;
	}
	

	public void setShader(Shader shader){
		this.shader = shader;
	}
	
	public Shader getShader( ){
		return this.shader;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

}
