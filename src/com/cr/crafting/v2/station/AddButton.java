package com.cr.crafting.v2.station;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.Game;

public class AddButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public AddButton(int xPos, int yPos) {
		super(new Rectangle(xPos, yPos, 150, 50));
		sprite = new Sprite("addbutton", Game.shader, new Transform());
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
		//Mouse.resetButton();
	}

	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}

	
	
}
