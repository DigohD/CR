package com.cr.entity.hero.materials;

import java.awt.Rectangle;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Hooverable;
import com.cr.input.Mouse;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class MaterialChoice extends Button implements Hooverable{

	private Sprite sprite, matSprite;
	private int xPos, yPos;
	private boolean isClicked, isHoover;
	private Material material;
	
	private Sprite flatBlack = new Sprite("flatblack");
	private Sprite flatWhite = new Sprite("flatwhite");
	
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
		
		Font matText = FontLoader.aquireFont(FontColor.WHITE);
		matText.setFont(CRString.create(material.getAmount() + ""));
		screen.renderFont(matText, xPos - 2, yPos + 30, 0.2f);
		FontLoader.releaseFont(matText);
	}

	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void clicked(){
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

	@Override
	public void renderHoover(Screen screen) {
		Font matText = FontLoader.aquireFont(FontColor.WHITE);
		matText.setFont(CRString.create(material.getName()));
		screen.renderStaticSprite(flatWhite, Input.getMousePosition().x + 6, Input.getMousePosition().y, 10.4f, 3.4f);
		screen.renderStaticSprite(flatBlack, Input.getMousePosition().x + 8, Input.getMousePosition().y + 2, 10, 3);
		screen.renderFont(matText, Input.getMousePosition().x + 14, Input.getMousePosition().y - 14, 0.2f);
		FontLoader.releaseFont(matText);
	}

	@Override
	public void setHoover(boolean bool) {
		isHoover = bool;
	}

	@Override
	public boolean isHoover() {
		return isHoover;
	}
	
	
	
}
