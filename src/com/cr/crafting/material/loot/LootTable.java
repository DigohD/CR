package com.cr.crafting.material.loot;

import java.util.ArrayList;
import java.util.LinkedList;

import com.cr.util.Randomizer;

public class LootTable {

	/*
	 * Material ID's
	 * 
	 * -----------------------------
	 * Bases
	 * -----------------------------
	 * 101 - Copper
	 * 102 - Rugged Cloth
	 * 103 - Scrap Wood
	 * 
	 * -----------------------------
	 * Essences
	 * -----------------------------
	 * 201 - Strange Powder
	 * 202 - Forest Soul
	 * 
	 * -----------------------------
	 * Minerals
	 * -----------------------------
	 * 301 - Pyrite
	 * 302 - Quartz
	 */
	
	private LinkedList<LootEntry> entries = new LinkedList<LootEntry>();
	private float totalWeight;
	
	public void addEntry(LootEntry entry){
		entry.setStartValue(totalWeight);
		totalWeight = totalWeight + entry.getWeight();
		entries.add(entry);
	}
	
	public int getLootID(){
		float lootRng = Randomizer.getFloat(0, totalWeight);
		for(int i = 0; i < entries.size(); i++){
			float start = entries.get(i).startValue;
			float end = entries.get(i).startValue + entries.get(i).getWeight();
			
			if(start <= lootRng && end > lootRng){
				return entries.get(i).getID();
			}
		}
		
		
		System.err.println("NO LOOT GENERATED!!!");
		System.exit(0);
		return 0;
	}
	
}
