package com.cr.gui;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Hooverable;
import com.cr.game.Game;

public class CRButton extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	
	public CRButton(String name, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		Transform t = new Transform();
		sprite = new Sprite(name, Game.shader, t);
		rect = new Rectangle(xPos, yPos, sprite.getSpriteWidth(), sprite.getSpriteHeight());

	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
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
