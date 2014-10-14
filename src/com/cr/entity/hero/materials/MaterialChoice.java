package com.cr.entity.hero.materials;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.property.Property;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Hooverable;
import com.cr.game.Game;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class MaterialChoice extends Button implements Hooverable{

	private Sprite matSprite;
	private int xPos, yPos;
	private boolean isHoover;
	private Material material;
	
	private Sprite flatBlack = new Sprite("flatblack", Game.shader, new Transform());
	private Sprite flatWhite = new Sprite("flatwhite", Game.shader, new Transform());
	
	public MaterialChoice(int xPos, int yPos, Material material) {
		super("slot", xPos, yPos);
		matSprite = material.getMaterialImage();
		this.material = material;
	}

	public void render(Screen screen) {
		super.render(screen);
		screen.renderStaticSprite(matSprite, xPos, yPos);
		
		Font matText = FontLoader.aquireFont(FontColor.WHITE);
		matText.setFont(CRString.create(material.getAmount() + ""));
		screen.renderFont(matText, xPos - 2, yPos + 30, 0.2f);
		FontLoader.releaseFont(matText);
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public void renderHoover(Screen screen) {
		Font matText = FontLoader.aquireFont(FontColor.WHITE);
		matText.setFont(CRString.create(material.getName()));
		int ySizeMod = material.getProperties().size() * 2;
		int xSizeMod = (material.getName().length() * 5) / 10;
		screen.renderStaticSprite(flatWhite, Input.getMousePosition().x + 6, Input.getMousePosition().y, 10.4f + xSizeMod, 6f + ySizeMod);
		screen.renderStaticSprite(flatBlack, Input.getMousePosition().x + 8, Input.getMousePosition().y + 2, 10 + xSizeMod, 5.6f + ySizeMod);

		screen.renderFont(matText, Input.getMousePosition().x + 14, Input.getMousePosition().y - 14, 0.2f);
		
		int row = 0;
		for(Property x : material.getProperties()){
			matText.setFont(CRString.create(x.getName()));
			screen.renderFont(matText, Input.getMousePosition().x + 22, Input.getMousePosition().y + 30 + row * 20, 0.2f);
			row++;
		}
		
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
