package com.cr.stats;

import java.util.HashMap;

public class StatsSheet {
	
	public HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();
	
	public enum StatID {STRENGTH, AGILITY, INTELLIGENCE, TOUGHNESS,
						HP_NOW, HP_MAX,
						
	
						/* Non-character Stats*/
						DAMAGE_BASE, DAMAGE_DICE, COOLDOWN
	}
	
	public StatsSheet(){
		sheet.put(StatID.STRENGTH, new Stat("Strength", 10));
		sheet.put(StatID.AGILITY, new Stat("Agility", 10));
		sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 10));
		sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
		
		sheet.put(StatID.HP_NOW, new Stat("HP_Now", 100));
		sheet.put(StatID.HP_MAX, new Stat("HP_Max", 100));
	}
	
	public void addMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.addAddmod(mod.getSourceID(), mod.getAmount());
		else
			affected.addMulmod(mod.getSourceID(), mod.getAmount());
	}
	
	public Stat getStat(StatID ID){
		return sheet.get(ID);
	}
}
