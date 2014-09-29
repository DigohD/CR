package com.cr.util;

import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;

public class RPCalc {

	public static float calculateDamage(float damage, StatsSheet attackerSheet, StatsSheet defenderSheet){
		float baseDamage = 1 + 
				(damage * 
				attackerSheet.getStat(StatID.PHYSICAL_POWER).getTotal() / 
				defenderSheet.getStat(StatID.LEVEL).getTotal() / 100);
		float totalDamage = baseDamage * (1f -
				((float) (Math.log(defenderSheet.getStat(StatID.ARMOR_RATING).getTotal() + 1f)) /
				5));
		return totalDamage;
	}
	
}
