package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		material = new Material(2f, new Vector3f(0.8f,0.8f,1.8f), new Vector3f(1f,1f,1.6f), new Vector3f(0f,0f,0f));
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
