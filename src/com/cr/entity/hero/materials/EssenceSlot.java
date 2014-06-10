package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.essences.ForestSoul;
import com.cr.crafting.material.essences.StrangePowder;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.hero.materials.Materials.Essences;

public class EssenceSlot extends MaterialSlot{

	private Essences type;
	
	public EssenceSlot(int xIndex, int yIndex, Essences type) {
		super(xIndex * 52 + ((Window.getWidth() - 800) / 2) + 10, 
				yIndex * 52 + ((Window.getHeight() - 600) / 2) + 10);
		String materialName = null;
		this.type = type;
		switch(type){
			case STRANGE_POWDER:
				materialName = "strangepowder";
				name = "Strange Powder";
				break;
			case FOREST_SOUL:
				materialName = "forestsoul";
				name = "Forest Soul";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialSprite = new Sprite(imageName);
	}

	
	
	public Essences getType() {
		return type;
	}

	@Override
	public Material getMaterial() {
		switch(type){
			case STRANGE_POWDER:
				return new StrangePowder();
			case FOREST_SOUL:
				return new ForestSoul();
		}
		return null;
	}

}
