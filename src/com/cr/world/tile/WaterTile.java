package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		material = new Material(25f, new Vector3f(0.2f,0.2f,1.2f), new Vector3f(0.1f,0.1f,0.1f), new Vector3f(0f,0f,0f));
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
