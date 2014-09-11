package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.item.stats.Stat;

public abstract class Pattern {

	protected ArrayList<Stat> stats;
	protected boolean isWeapon;
	
	public Pattern(boolean isWeapon){
		this.isWeapon = isWeapon;
	}
	
	public void createStatsFromMaterials(ArrayList<Material> materials){
		stats = new ArrayList<Stat>();
		for(Material x : materials){
			stats.addAll(x.generateStat(isWeapon));
		}
	}
	
	public abstract Item generateItem();
	public abstract Sprite getSprite();
	
}
