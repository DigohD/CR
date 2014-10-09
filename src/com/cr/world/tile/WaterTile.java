package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;


public class WaterTile extends Tile{
	
	public WaterTile() {
		material = new Material(15f, new Vector3f(0.5f,0.5f,1.2f), new Vector3f(1.2f,1.2f,1.6f), new Vector3f(0.2f,0.2f,0.2f));
		//material = new Material(2.0f, 5f, 0.7f, 0.2f);
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
}
