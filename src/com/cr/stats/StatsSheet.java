package com.cr.stats;

import java.util.HashMap;

public class StatsSheet {
	
	protected HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();
	
	public enum StatID {STRENGTH, AGILITY, INTELLIGENCE, TOUGHNESS,
						HP_NOW, HP_MAX, ARMOR,
						
	
						/* Non-character Stats*/
						DAMAGE_BASE, DAMAGE_DICE, COOLDOWN
	}
	
	public StatsSheet(){
		super();
		sheet.put(StatID.STRENGTH, new Stat("Strength", 10));
		sheet.put(StatID.AGILITY, new Stat("Agility", 10));
		sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 10));
		sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
		
		sheet.put(StatID.HP_NOW, new Stat("Current HP", 100));
		sheet.put(StatID.HP_MAX, new Stat("Max HP", 100));
		sheet.put(StatID.ARMOR, new Stat("Armor", 0));
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
	
	public static String getStatString(StatID id){
		switch(id){
			case STRENGTH:
				return "Strength";
			case AGILITY:
				return "Agility";
			case INTELLIGENCE:
				return "Intelligence";
			case TOUGHNESS:
				return "Toughness";
			case HP_NOW:
				return "Current HP";
			case HP_MAX:
				return "Max HP";
			case ARMOR:
				return "Armor";
			default:
				break;
		}
		
		
		return null;
		
	}
}
