package com.cr.world.tile;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Material;

public class GrassTile extends Tile{

	public GrassTile() {
		super();

		//material = new Material(2.1f, new Vector3f(2.2f, 2.3f, 2.2f), new Vector3f(0.1f,0.1f,0.1f), new Vector3f(0f,0f,0f));
		material = new Material(2.9f, new Vector3f(2.2f, 2.2f, 3.2f), new Vector3f(1.3f,1.3f,1.3f), new Vector3f(0.2f,0.2f,0.2f));

		row = 0f;
		col = 1f;
	}


}
