package com.cr.entity.hero.inventory;

import java.awt.image.BufferedImage;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.game.Game;

public class InventoryButton implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	
	public InventoryButton(int xPos, int yPos) {
		super();
		sprite = new Sprite("inventorybutton", Game.shader, new Transform());
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return null;
	}

}
