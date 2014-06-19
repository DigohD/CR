package com.cr.crafting.ui;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.Renderable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class SliderArrow extends Button implements Renderable{

	private Sprite sprite;
	private int xPos, yPos;
	private boolean isClicked;
	private float ratio;
	
	
	public SliderArrow(int xPos, int yPos) {
		super(new Rectangle(xPos - 20, yPos, 80, 34));
		sprite = new Sprite("sliderarrow");
		this.xPos = xPos;
		this.yPos = yPos;
		resetsButton = false;
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
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		isClicked = true;
		if(Input.getMousePosition().x < xOffset + 565 && Input.getMousePosition().y > xOffset + 35){
			xPos = (int) (Input.getMousePosition().x - 19);
			rect = new Rectangle(xPos - 20, yPos, 80, 34);
			float min = xOffset + 35;
			float max = xOffset + 565;
			float sliderLength = max - min;
			float arrowPos = xPos + 19 - min;
			ratio = arrowPos / sliderLength;
		}
	}

	public boolean isClicked() {
		if(isClicked){
//			isClicked = false;
			return true;
		}
		return false;
	}

	public float getRatio() {
		return ratio;
	}
	
	

	
	
}
