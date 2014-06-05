package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.game.Game;
import com.cr.resource.ImageLoader;

public class BaseSlot extends MaterialSlot{

	private Base type;
	
	public BaseSlot(int xIndex, int yIndex, Base type) {
		super(xIndex * 52 + ((Game.WIDTH - 800) / 2) + 10, 
				yIndex * 52 + ((Game.HEIGHT - 600) / 2) + 10);
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
		materialImage = ImageLoader.getImage(imageName);
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
