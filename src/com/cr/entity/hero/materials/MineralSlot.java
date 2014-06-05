package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.essences.StrangePowder;
import com.cr.crafting.material.minerals.Pyrite;
import com.cr.crafting.material.minerals.Quartz;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.entity.hero.materials.Materials.Essences;
import com.cr.entity.hero.materials.Materials.Minerals;
import com.cr.game.Game;
import com.cr.resource.ImageLoader;

public class MineralSlot extends MaterialSlot{

	private Minerals type;
	
	public MineralSlot(int xIndex, int yIndex, Minerals type) {
		super(xIndex * 52 + ((Game.WIDTH - 800) / 2) + 10, 
				yIndex * 52 + ((Game.HEIGHT - 600) / 2) + 10);
		String materialName = null;
		this.type = type;
		switch(type){
			case PYRITE:
				materialName = "pyrite";
				name = "Pyrite";
				break;
			case QUARTZ:
				materialName = "quartz";
				name = "Quartz";
				break;
		}
		setMaterialImage(materialName);
	}

	@Override
	public void setMaterialImage(String imageName) {
		materialImage = ImageLoader.getImage(imageName);
	}

	public Minerals getType() {
		return type;
	}

	@Override
	public Material getMaterial() {
		switch(type){
			case PYRITE:
				return new Pyrite();
			case QUARTZ:
				return new Quartz();
		}
		return null;
	}

}
