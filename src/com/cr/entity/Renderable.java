package com.cr.entity;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;

public interface Renderable {
	
	public void render(Screen screen);
	public Sprite getSprite();
}
