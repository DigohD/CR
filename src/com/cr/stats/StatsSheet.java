package com.cr.stats;

import java.util.HashMap;

public class StatsSheet {
	
	protected HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();
	private boolean isHero;
	
	public enum StatID {
						/*Primary Stats*/
						LEVEL,
						STRENGTH, AGILITY, INTELLIGENCE, TOUGHNESS,
						HP_NOW, HP_MAX,
						
						/*Mobility*/
						MOVEMENT_SPEED,
						
						/*Secondary Stats*/
						ARMOR, ARMOR_RATING, PHYSICAL_POWER, RAPIDNESS, SPELL_POWER,
						
						/*On hit Effects*/
						LIFE_ON_HIT,
	
						/*Active Passive*/
						LIFE_REGEN,
						
						/* Non-character Stats*/
						DAMAGE_BASE, DAMAGE_DICE, COOLDOWN
	}
	
	public StatsSheet(){
		super();
	}
	
	public StatsSheet(boolean newIsHero){
		super();
		isHero = newIsHero;
		if(isHero){
			sheet.put(StatID.LEVEL, new Stat(StatID.LEVEL, "Level", 1, isHero));
			sheet.put(StatID.STRENGTH, new Stat(StatID.STRENGTH, "Strength", 10, isHero));
			sheet.put(StatID.AGILITY, new Stat(StatID.AGILITY, "Agility", 10, isHero));
			sheet.put(StatID.INTELLIGENCE, new Stat(StatID.INTELLIGENCE, "Intelligence", 10, isHero));
			sheet.put(StatID.TOUGHNESS, new Stat(StatID.TOUGHNESS, "Toughness", 10000, isHero));
			sheet.put(StatID.HP_MAX, new Stat(StatID.HP_MAX, "Max Hp", 100, isHero));
			
			sheet.put(StatID.MOVEMENT_SPEED, new Stat(StatID.MOVEMENT_SPEED, "Movement Speed", 1f, isHero));
			
			sheet.put(StatID.ARMOR, new Stat(StatID.ARMOR, "Armor", 0, isHero));
			sheet.put(StatID.ARMOR_RATING, new Stat(StatID.ARMOR_RATING, "Armor Rating", 0, isHero));
			sheet.put(StatID.PHYSICAL_POWER, new Stat(StatID.PHYSICAL_POWER, "Physical Power", 0, isHero));
			sheet.put(StatID.RAPIDNESS, new Stat(StatID.RAPIDNESS, "Rapidness", 0, isHero));
			sheet.put(StatID.SPELL_POWER, new Stat(StatID.SPELL_POWER, "Spell Power", 0, isHero));
			
			sheet.put(StatID.LIFE_ON_HIT, new Stat(StatID.LIFE_ON_HIT, "Life On Hit", 0, isHero));
			sheet.put(StatID.LIFE_REGEN, new Stat(StatID.LIFE_REGEN, "Life Regen /5s", 0, isHero));
			
			updateInternalStats();
			
			sheet.put(StatID.HP_NOW, new Stat(StatID.HP_NOW, "Current Hp", sheet.get(StatID.HP_MAX).getTotal(), isHero));
		}
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
			case LEVEL:
				return "Level";
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
		
		sheet.get(StatID.ARMOR_RATING).removeAddmod(StatsSheet.getStatString(StatID.ARMOR));
		sheet.get(StatID.ARMOR_RATING).addAddmod(StatsSheet.getStatString(StatID.ARMOR), 
				sheet.get(StatID.ARMOR).getTotal() / sheet.get(StatID.LEVEL).getTotal() );
	}
}
