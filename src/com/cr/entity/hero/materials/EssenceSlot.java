package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.crafting.material.essences.StrangePowder;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.entity.hero.materials.Materials.Essences;
import com.cr.game.Game;
import com.cr.resource.ImageLoader;

public class EssenceSlot extends MaterialSlot{

	private Essences type;
	
	public EssenceSlot(int xIndex, int yIndex, Essences type) {
		super(xIndex * 52 + ((Game.WIDTH - 800) / 2) + 10, 
				yIndex * 52 + ((Game.HEIGHT - 600) / 2) + 10);
		String materialName = null;
		this.type = type;
		switch(type){
			case STRANGE_POWDER:
				materialName = "strangepowder";
				name = "Strange Powder";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialImage = ImageLoader.getImage(imageName);
	}

	
	
	public Essences getType() {
		return type;
	}

	@Override
	public Material getMaterial() {
		switch(type){
			case STRANGE_POWDER:
				return new StrangePowder();
		}
		return null;
	}

}
