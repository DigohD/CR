package com.cr.entity.hero.materials;

import com.cr.entity.hero.materials.Materials.Base;
import com.cr.entity.hero.materials.Materials.Essences;
import com.cr.entity.hero.materials.Materials.Minerals;
import com.cr.game.Game;
import com.cr.resource.ImageLoader;

public class MineralSlot extends MaterialSlot{

	public MineralSlot(int xIndex, int yIndex, Minerals type) {
		super(xIndex * 52 + ((Game.WIDTH - 800) / 2) + 10, 
				yIndex * 52 + ((Game.HEIGHT - 600) / 2) + 10);
		String materialName = null;
		switch(type){
			case PYRITE:
				materialName = "pyrite";
				break;
			case QUARTZ:
				materialName = "quartz";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialImage = ImageLoader.getImage(imageName);
	}

}
