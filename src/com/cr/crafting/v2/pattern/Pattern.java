package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.stats.StatMod;

public abstract class Pattern {

	protected ArrayList<StatMod> stats;
	protected boolean isWeapon;
	
	public Pattern(boolean isWeapon){
		this.isWeapon = isWeapon;
	}
	
	public void createStatsFromMaterials(ArrayList<Material> materials){
		stats = new ArrayList<StatMod>();
		for(Material x : materials){
			stats.addAll(x.generateStat(isWeapon));
		}
		System.out.println("StatCount: " + stats.size());
	}
	
	protected void applyQualities(Item i, ArrayList<Material> materials, String sourceID){
		for(Material x : materials){
			x.applyQualityBonuses(i, sourceID);
		}
	}
	
	public abstract Item generateItem(ArrayList<Material> materials);
	public abstract Sprite getSprite();
	
}
