package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.hero.materials.Materials.Base;

public class BaseSlot extends MaterialSlot{

	private Base type;
	
	public BaseSlot(int xIndex, int yIndex, Base type) {
		super(xIndex * 52 + ((Window.getWidth() - 800) / 2) + 10, 
				yIndex * 52 + ((Window.getHeight() - 600) / 2) + 10);
		String materialName = null;
		this.type = type;
		switch(type){
			case COPPER:
				materialName = "copper";
				name = "Copper";
				break;
			case RUGGED_CLOTH:
				materialName = "basiccloth";
				name = "Basic Cloth";
				break;
			case SCRAP_WOOD:
				materialName = "scrapwood";
				name = "Scrap Wood";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialSprite = new Sprite(imageName);
	}
	
	public Base getBaseType(){
		return type;
	}

	@Override
	public Material getMaterial() {
		switch(type){
			case COPPER:
				return new Copper();
			case RUGGED_CLOTH:
				return null; 
			case SCRAP_WOOD:
				return null;
		}
		return null;
	}

}
