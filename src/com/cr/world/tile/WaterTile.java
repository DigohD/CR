package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		//material = new Material(25f, new Vector3f(1.2f,1.2f,1.9f), new Vector3f(1.2f,1.2f,1.9f), new Vector3f(0f,0f,0f));
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
