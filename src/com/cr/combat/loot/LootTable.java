package com.cr.combat.loot;

import java.util.ArrayList;
import java.util.LinkedList;

import com.cr.util.Randomizer;

public class LootTable {
	
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
