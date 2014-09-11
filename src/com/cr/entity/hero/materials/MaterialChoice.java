package com.cr.entity.hero.materials;

import java.awt.Rectangle;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public class MaterialChoice extends Button{

	private Sprite sprite, matSprite;
	private int xPos, yPos;
	private boolean isClicked;
	private Material material;
	
	public MaterialChoice(int xPos, int yPos, Material material) {
		super(new Rectangle(xPos, yPos, 50, 50));
		sprite = new Sprite("slot");
		matSprite = material.getMaterialImage();
		this.material = material;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void render(Screen screen) {
		screen.renderStaticSprite(sprite, xPos, yPos);
		screen.renderStaticSprite(matSprite, xPos, yPos);
	}

	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void clicked() {
		isClicked = true;
		Mouse.resetButton();
	}

	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}

	public Material getMaterial() {
		return material;
	}
	
	
	
}
