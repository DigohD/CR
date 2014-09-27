package com.cr.stats;

import java.util.HashMap;

public class StatsSheet {
	
	protected HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();
	
	public enum StatID {STRENGTH, AGILITY, INTELLIGENCE, TOUGHNESS,
						HP_NOW, HP_MAX, ARMOR,
						
						
						/*On hit Effects*/
						LIFE_ON_HIT,
	
						/*Passive*/
						LIFE_REGEN,
						
						/* Non-character Stats*/
						DAMAGE_BASE, DAMAGE_DICE, COOLDOWN
	}
	
	public StatsSheet(){
		super();
		sheet.put(StatID.STRENGTH, new Stat("Strength", 10));
		sheet.put(StatID.AGILITY, new Stat("Agility", 10));
		sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 10));
		sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
		
		sheet.put(StatID.HP_NOW, new Stat("Current Hp", 100));
		sheet.put(StatID.HP_MAX, new Stat("Max Hp", 100));
		sheet.put(StatID.ARMOR, new Stat("Armor", 0));
		
		sheet.put(StatID.LIFE_ON_HIT, new Stat("Life On Hit", 0));
		sheet.put(StatID.LIFE_REGEN, new Stat("Life Regen", 0));
	}
	
	public void addMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.addAddmod(mod.getSourceID(), mod.getAmount());
		else
			affected.addMulmod(mod.getSourceID(), mod.getAmount());
	}
	
	public void removeMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.removeAddmod(mod.getSourceID(), mod.getAmount());
		else
			affected.removeMulmod(mod.getSourceID(), mod.getAmount());
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
			case LIFE_ON_HIT:
				return "Life On Hit";
			case LIFE_REGEN:
				return "Life Regen";
			default:
				break;
		}
		
		
		return null;
		
	}
}
