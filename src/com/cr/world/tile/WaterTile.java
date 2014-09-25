package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		material = new Material(25.0f, new Vector3f(1,1,2), new Vector3f(1,1,1), new Vector3f(0,0,0));
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
