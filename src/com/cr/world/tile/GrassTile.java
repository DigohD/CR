package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;

public class GrassTile extends Tile{

	public GrassTile() {
		super();
		material = new Material(2f, new Vector3f(1.7f,1.7f,1.7f), new Vector3f(1.2f,1.2f,1.2f), new Vector3f(0.3f,0.3f,0.3f));
		row = 0f;
		col = 1f;
	}


}
