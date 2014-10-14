package com.cr.entity.hero.inventory;

import java.awt.Point;
import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.Renderable;
import com.cr.game.Game;

public class Button implements Renderable{

	protected Rectangle rect;
	protected Sprite sprite;
	protected int xPos, yPos;
	protected boolean isClicked;
	protected boolean resetsButton;
	
	public Button(String name, int xPos, int yPos){
		sprite = new Sprite(name, Game.shader, new Transform());
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.rect = new Rectangle(xPos, yPos, sprite.getSpriteWidth(), sprite.getSpriteHeight());
		
		resetsButton = true;
		Input.addButton(this);
	}
	
	public Button(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		
		resetsButton = true;
		Input.addButton(this);
	}

	public void removeFromInput(){
		Input.removeButton(this);
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
	}

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

	public void setResetsButton(boolean resetsButton) {
		this.resetsButton = resetsButton;
	}
	
	public void recieveClick(Point p){
		if(rect.contains(p))
			clicked();
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	public Rectangle getRect(){
		return rect;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
	
}
