package com.cr.world.tile;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.world.TileMap;

public class WaterTile extends Tile{
	
//	Sprite sprite;
	
	public WaterTile() {
//		sprite = new Sprite("ripple", TileMap.getWaterShader());
		row = 0f;
		col = 2f;
		walkable = false;
	}

//	public void render(Screen screen){
//		glActiveTexture(GL_TEXTURE1);
//		screen.renderSprite(sprite, 0, 0);
//		glActiveTexture(GL_TEXTURE0);
//	}
	
}
