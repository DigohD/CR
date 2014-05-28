package com.cr.entity.hero.materials;

import com.cr.entity.hero.materials.Materials.Base;
import com.cr.game.Game;
import com.cr.resource.ImageLoader;

public class BaseSlot extends MaterialSlot{

	public BaseSlot(int xIndex, int yIndex, Base type) {
		super(xIndex * 52 + ((Game.WIDTH - 800) / 2) + 10, 
				yIndex * 52 + ((Game.HEIGHT - 600) / 2) + 10);
		String materialName = null;
		switch(type){
			case COPPER:
				materialName = "copper";
				break;
			case RUGGED_CLOTH:
				materialName = "basiccloth";
				break;
			case SCRAP_WOOD:
				materialName = "scrapwood";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialImage = ImageLoader.getImage(imageName);
	}

}
