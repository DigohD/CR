package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.game.Game;

public class ExitButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public ExitButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 150, 50));
		sprite = new Sprite("exitbutton", Game.shader, new Transform());
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void clicked() {
		isClicked = true;
	}

	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}

	
	
}
