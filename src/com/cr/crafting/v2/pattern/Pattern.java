package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.item.stats.Stat;

public abstract class Pattern {

	protected float ASMod, damageMod;
	protected ArrayList<Stat> stats = new ArrayList<Stat>();
	protected boolean isWeapon;
	
	public Pattern(boolean isWeapon){
		this.isWeapon = isWeapon;
	}
	
	public void createStatsFromMaterials(ArrayList<Material> materials){
		for(Material x : materials){
			stats.add(x.generateStat(isWeapon));
		}
	}
	
}
