package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		material = new Material(50.0f, new Vector3f(0.5f,0.5f,1.97f), new Vector3f(0.4f,0.4f,1.6f), new Vector3f(0.2f,0.2f,0.2f));
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
