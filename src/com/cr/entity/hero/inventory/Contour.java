package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;

public class Contour implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	
	public Contour() {
		sprite = new Sprite("contour");
		this.xPos = 460;
		this.yPos = 30;
	}
	
	public Contour(int xOffset, int yOffset) {
		sprite = new Sprite("contour");
		this.xPos = 460 + xOffset;
		this.yPos = 30 + yOffset;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
}
