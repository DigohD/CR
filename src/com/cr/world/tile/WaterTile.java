package com.cr.world.tile;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Animation;
import com.cr.engine.graphics.Sprite;
import com.cr.world.World;

public class WaterTile extends Tile{
	
	private Sprite sprite;
	private Animation anim;

	public WaterTile() {
		
		
		sprite = new Sprite("", 1, 3, 0, 0, World.getShader(), new Transform());
		anim = new Animation(sprite, 10);
		row = 0f;
		col = 2f;
		walkable = false;
	}

	
	public void animate(){
		
	}
	

}
