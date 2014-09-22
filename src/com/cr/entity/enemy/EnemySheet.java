package com.cr.entity.enemy;

import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;

public class EnemySheet extends StatsSheet{

	public EnemySheet(){
		sheet.put(StatID.STRENGTH, new Stat("Strength", 10));
		sheet.put(StatID.AGILITY, new Stat("Agility", 10));
		sheet.put(StatID.INTELLIGENCE, new Stat("Intelligence", 10));
		sheet.put(StatID.TOUGHNESS, new Stat("Toughness", 10));
		
		sheet.put(StatID.HP_NOW, new Stat("HP_Now", 10));
		sheet.put(StatID.HP_MAX, new Stat("HP_Max", 10));
	}
	
}
