package com.cr.world.tile;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Animation;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.game.EntityManager;
import com.cr.world.TileLayer;
import com.cr.world.TileMap;
import com.cr.world.World;


public class WaterTile extends Tile{
	
	private Sprite sprite;
	private static Animation anim;
	
	public WaterTile() {
		sprite = new Sprite("water", 1, 6, 0, 0, World.getShader(), TileMap.getTransform());
		anim = new Animation(sprite, 12);
//		anim = new Animation(20);
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
	public void tick(TileLayer tl, int offset){
//		anim.animateWater(tl.getMesh(), 0, 2, 4, 4, offset);
		anim.animateRow(0);
	}
	
	public void render(Screen screen, int xp, int yp, int xOffset, int yOffset){
		xp = xp * 58 + 3;
		yp = yp * 38 + 3;
		
		screen.renderSprite(sprite, xp, yp);
	}

}
