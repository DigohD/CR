package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;

public class GrassTile extends Tile{

	public GrassTile() {
		super();
		material = new Material(0.2f, new Vector3f(0.7f,0.7f,1.0f), new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0f,0f,0f));
		row = 0f;
		col = 1f;
	}


}
