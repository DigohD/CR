package com.cr.world.tile;

import com.cr.engine.graphics.Material;
import com.cr.engine.graphics.Texture;

public abstract class Tile {
	
	private static Texture texture = new Texture("tileatlas32");
	
	public final static float TILE_ATLAS_ROWS = 4;
	public final static float TILE_ATLAS_COLS = 4;
	
	private static int width, height;
	protected float row, col;
	protected boolean walkable;
	
	protected Material material;
	
	public Tile(){
		material = new Material();
		width = texture.getWidth();
		height = texture.getHeight();
		walkable = true;
	}

	public static Texture getTexture() {
		return texture;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public static int getTileWidth() {
		return (int) (width/TILE_ATLAS_COLS);
	}

	public static int getTileHeight() {
		return (int) (height / TILE_ATLAS_ROWS);
	}
	
	public static int getAtlasWidth() {
		return width;
	}

	public static int getAtlasHeight() {
		return height;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public float getRow() {
		return row;
	}

	public float getCol() {
		return col;
	}

}
