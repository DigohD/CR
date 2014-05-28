package com.cr.entity.hero.materials;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.ItemSlot;
import com.cr.game.Display;

public class Materials implements Tickable, Renderable{

	public enum MaterialType {BASE, ESSENCES, MINERALS};
	
	private static MaterialType activeTab;
	
	public enum Base {COPPER, RUGGED_CLOTH, SCRAP_WOOD};
	public enum Essences {STRANGE_POWDER};
	public enum Minerals {PYRITE, QUARTZ};
	
	private static HashMap<Base, Integer> baseMap;
	private static HashMap<Essences, Integer> essencesMap;
	private static HashMap<Minerals, Integer> mineralsMap;
	
	private BaseSlot[] bases = new BaseSlot[100];
	
	public Materials(){
		baseMap = new HashMap<Base, Integer>();
		essencesMap = new HashMap<Essences, Integer>();
		mineralsMap = new HashMap<Minerals, Integer>();
		
		for(Base base : Base.values()){
			  baseMap.put(base, 0);
		}for(Essences essence : Essences.values())
			  essencesMap.put(essence, 0);
		for(Minerals mineral : Minerals.values())
			  mineralsMap.put(mineral, 0);
		
		addBase(Base.COPPER, 100);
		addBase(Base.RUGGED_CLOTH, 15);
	}
	
	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void render(Graphics2D g) {
		if(activeTab == MaterialType.BASE){
			int counter = 0;
			for(Base base : Base.values()){
				if(baseMap.get(base) != 0){
					bases[counter] = new BaseSlot(counter, 0, base);
					bases[counter].setAmount(baseMap.get(base));
					bases[counter].render(g);
					counter++;
				}
			}
		}
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
	public static void buttonClicked(Button button){
		
	}

	public static void setActiveTab(MaterialType newTab) {
		activeTab = newTab;
	}
	
	public void addBase(Base type, int amount){
		baseMap.put(type, baseMap.get(type) + amount);
	}
	
	public void addEssence(Essences type, int amount){
		essencesMap.put(type, essencesMap.get(type) + amount);
	}
	
	public void addMinerals(Minerals type, int amount){
		mineralsMap.put(type, mineralsMap.get(type) + amount);
	}
}
