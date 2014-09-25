package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;

public class GrassTile extends Tile{

	public GrassTile() {
		super();
		material = new Material(50.0f, new Vector3f(0.2f,0.7f,0.2f), new Vector3f(0.4f,1.6f,0.4f), new Vector3f(0.2f,0.2f,0.2f));
		row = 0f;
		col = 1f;
	}


}
