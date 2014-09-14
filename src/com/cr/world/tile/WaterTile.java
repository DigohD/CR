package com.cr.world.tile;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Animation;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.game.EntityManager;
import com.cr.world.TileMap;
import com.cr.world.World;


public class WaterTile extends Tile{
	
	private Sprite sprite;
	private Animation anim;
	
	public WaterTile() {
		sprite = new Sprite("water", 1, 6, 0, 0, World.getShader(), TileMap.getTransform());
		anim = new Animation(sprite, 1500);
		row = 0f;
		col = 2f;
		walkable = false;
	}
	
	public void tick(){
		anim.animateRow(0);
	}
	
	public void render(Screen screen, int xp, int yp){
		xp = xp * 58;
		yp = yp * 38;
		
//		xp -= (EntityManager.getHero().getX() - Window.getWidth()/2);
//		yp -= (EntityManager.getHero().getY() - Window.getHeight()/2);
		
		screen.renderSprite(sprite, xp, yp);
	}

}
