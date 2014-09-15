package com.cr.crafting.v2.station;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.KnifePattern;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.item.Item;

public class Forge {
	
	private Pattern pattern;
	private ArrayList<Material> materials = new ArrayList<Material>();
	private int heat, time, minHeat, minTime, maxHeat, maxTime;
	private boolean materialAdded = false;
	
	public Forge(){
		minHeat = 300;
		maxHeat = 2000;
		minTime = 15;
		maxTime = 360;
	}
	
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void fillPossiblePatterns(ArrayList<Pattern> patterns){
		patterns.add(new KnifePattern());
	}
	
	public void addMaterial(Material material){
		if(materials.size() == 0 && material.isPrimary()){
			materialAdded = true;
			material.resetSpans();
			materials.add(material);
		}else if(materials.size() > 0 && !material.isPrimary()){
			materialAdded = true;
			material.resetSpans();
			materials.add(material);
		}
	}
	
	public void process(){
		for(Material x : materials){
			x.process(heat, time, materials);
		}
	}
	
	public void craft(){
		pattern.createStatsFromMaterials(materials);
		Item i = pattern.generateItem();
		Inventory.addItem(i);
	}

	public int getMinHeat() {
		return minHeat;
	}

	public int getMinTime() {
		return minTime;
	}

	public int getMaxHeat() {
		return maxHeat;
	}

	public int getMaxTime() {
		return maxTime;
	}
	
	
}
