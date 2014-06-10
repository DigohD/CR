package com.cr.entity.hero.materials;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.minerals.Pyrite;
import com.cr.crafting.material.minerals.Quartz;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.hero.materials.Materials.Minerals;

public class MineralSlot extends MaterialSlot{

	private Minerals type;
	
	public MineralSlot(int xIndex, int yIndex, Minerals type) {
		super(xIndex * 52 + ((Window.getWidth() - 800) / 2) + 10, 
				yIndex * 52 + ((Window.getHeight() - 600) / 2) + 10);
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
		materialSprite = new Sprite(imageName);
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
