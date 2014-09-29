package com.cr.stats;

import java.util.HashMap;

public class StatsSheet {
	
	protected HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();
	
	public enum StatID {
						/*Primary Stats*/
						STRENGTH, AGILITY, INTELLIGENCE, TOUGHNESS,
						HP_NOW, HP_MAX,
						
						/*Secondary Stats*/
						ARMOR, PHYSICAL_POWER, RAPIDNESS, SPELL_POWER,
						
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
		sheet.put(StatID.PHYSICAL_POWER, new Stat("Physical Power", 0));
		sheet.put(StatID.RAPIDNESS, new Stat("Rapidness", 0));
		sheet.put(StatID.SPELL_POWER, new Stat("Spell Power", 0));
		
		sheet.put(StatID.LIFE_ON_HIT, new Stat("Life On Hit", 0));
		sheet.put(StatID.LIFE_REGEN, new Stat("Life Regen /5s", 0));
		
		updateInternalStats();
	}
	
	public void addMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.addAddmod(mod.getSourceID(), mod.getAmount());
		else
			affected.addMulmod(mod.getSourceID(), mod.getAmount());
		updateInternalStats();
	}
	
	public void removeMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.removeAddmod(mod.getSourceID());
		else
			affected.removeMulmod(mod.getSourceID());
		updateInternalStats();
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
			case PHYSICAL_POWER:
				return "Physical Power";
			case RAPIDNESS:
				return "Rapidness";
			case SPELL_POWER:
				return "Spell Power";
			case LIFE_ON_HIT:
				return "Life On Hit";
			case LIFE_REGEN:
				return "Life Regen /5s";
			default:
				break;
		}
		return null;
	}
	
	public void updateInternalStats(){
		sheet.get(StatID.HP_MAX).removeAddmod(StatsSheet.getStatString(StatID.TOUGHNESS));
		sheet.get(StatID.HP_MAX).addAddmod(StatsSheet.getStatString(StatID.TOUGHNESS), sheet.get(StatID.TOUGHNESS).getTotal() * 5);
		
		sheet.get(StatID.PHYSICAL_POWER).removeAddmod(StatsSheet.getStatString(StatID.STRENGTH));
		sheet.get(StatID.PHYSICAL_POWER).addAddmod(StatsSheet.getStatString(StatID.STRENGTH), sheet.get(StatID.STRENGTH).getTotal() * 5);
		
		sheet.get(StatID.RAPIDNESS).removeAddmod(StatsSheet.getStatString(StatID.AGILITY));
		sheet.get(StatID.RAPIDNESS).addAddmod(StatsSheet.getStatString(StatID.AGILITY), sheet.get(StatID.AGILITY).getTotal() * 5);
		
		sheet.get(StatID.SPELL_POWER).removeAddmod(StatsSheet.getStatString(StatID.INTELLIGENCE));
		sheet.get(StatID.SPELL_POWER).addAddmod(StatsSheet.getStatString(StatID.INTELLIGENCE), sheet.get(StatID.INTELLIGENCE).getTotal() * 5);
	}
}
