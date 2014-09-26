package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;

public class GrassTile extends Tile{

	public GrassTile() {
		super();
		material = new Material(1.0f, new Vector3f(1.6f,1.7f,1.6f), new Vector3f(1.8f,1.6f,1.8f), new Vector3f(0f,0f,0f));
		row = 0f;
		col = 1f;
	}


}
