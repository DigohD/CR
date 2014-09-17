package com.cr.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.Bitmap;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.shader.Shader;
import com.cr.game.Game;
import com.cr.world.tile.Tile;

public class TileLayer {
	
	protected int width, height;
	protected float depth;

	protected Bitmap bitmap;
	protected Mesh mesh;
	protected Shader shader;
	protected Transform transform;
	
	protected HashMap<Integer, Tile> tiles;
	
	protected float xLow = 0;
	protected float xHigh = 0;
	protected float yLow = 0;
	protected float yHigh = 0;
	
	protected float scaleFactor = 1f;
	
	public TileLayer(int width, int height, float depth){
		this.depth = depth;
		bitmap = new Bitmap(width, height);
		
		this.width = width;
		this.height = height;
		this.transform = TileMap.getTransform();
		
		this.shader = World.getShader();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public TileLayer(String name, Transform transform){
		this.transform = transform;
		bitmap = new Bitmap(name);
		
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
		this.transform = transform;
		this.shader = World.getShader();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public void generateTileLayer(){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		List<Vector2f> texCoords = new ArrayList<Vector2f>();
		
		float tWidth = Tile.getTileWidth();
		float tHeight = Tile.getTileHeight();
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
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
				
				vertices.add(new Vertex(new Vector3f(xPos, yPos, 0)));
				vertices.add(new Vertex(new Vector3f(xPos, yPos + tHeight + yOffset, 0)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos + tHeight + yOffset, 0)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos, 0)));
				
				texCoords.add(new Vector2f(xLow, yLow));
				texCoords.add(new Vector2f(xLow, yHigh));
				texCoords.add(new Vector2f(xHigh, yHigh));
				texCoords.add(new Vector2f(xHigh, yLow));
			}
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
		Vector2f[] texCoordArray = new Vector2f[texCoords.size()];
		
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		texCoords.toArray(texCoordArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
		
		mesh = new Mesh(vertexArray, texCoordArray, iArray, true);
		transform.scale(scaleFactor, scaleFactor, 1);
	}
	

	
	public void renderTileLayer(boolean water){
		transform.translate(0, 0, depth);
		if(!water){
			shader.bind();
			shader.setUniform("transformation", transform.getOrthoTransformation());
		}
		
		Tile.getTexture().bind();
		mesh.render();
		Tile.getTexture().unbind();
		shader.unbind();
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

	public Mesh getMesh() {
		return mesh;
	}

}
