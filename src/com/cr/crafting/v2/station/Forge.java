package com.cr.crafting.v2.station;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.pattern.armor.BreastplatePattern;
import com.cr.crafting.v2.pattern.armor.HelmPattern;
import com.cr.crafting.v2.pattern.armor.LeggingsPattern;
import com.cr.crafting.v2.pattern.weapon.KnifePattern;
import com.cr.engine.graphics.Screen;
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
		patterns.add(new HelmPattern());
		patterns.add(new BreastplatePattern());
		patterns.add(new LeggingsPattern());
	}
	
	public void addMaterial(Material material, int amount){
		if(materials.size() == 0 && material.isPrimary()){
			materialAdded = true;
			material.resetSpans();
			materials.add(material);
			material.setAmount(material.getAmount() - amount);
			material.setUsedAmount(amount);
		}else if(materials.size() > 0 && !material.isPrimary()){
			materialAdded = true;
			material.resetSpans();
			materials.add(material);
			material.setAmount(material.getAmount() - amount);
			material.setUsedAmount(amount);
		}
	}
	
	public void process(){
		System.out.println("Heat " + heat + " : Time " + time);
		for(Material x : materials){
			x.process(heat, time, materials);
			System.out.println(x.getName() + " State " + x.getState());
		}
	}
	
	public Item craft(){
		//System.out.println("Pattern: " + pattern + " , Materials: " + materials);
		Item i = pattern.generateItem(materials);
		i.statsInit();
		Inventory.addItem(i);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		return i;
	}

	public void renderProcess(Screen screen, int xOffset, int yOffset){
		for(Material x : materials){
			x.renderStatus(screen, xOffset, yOffset);
			yOffset = yOffset + 30;
		}
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
	
	public Material getBase(){
		return materials.get(0);
	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}
	
	
}
