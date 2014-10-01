package com.cr.util;

import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;

public class RPCalc {

	public static float calculateDamage(float damage, StatsSheet attackerSheet, StatsSheet defenderSheet){
		
//		System.out.println("Flat Damage done: " + damage);
		
		float baseDamage = damage * (1 + 
				(attackerSheet.getStat(StatID.PHYSICAL_POWER).getTotal() / 
				defenderSheet.getStat(StatID.LEVEL).getTotal() / 100));
		
//		System.out.println("Damage, PP & Level: " + damage * (1 + 
//				(attackerSheet.getStat(StatID.PHYSICAL_POWER).getTotal() / 
//				defenderSheet.getStat(StatID.LEVEL).getTotal() / 100)));
//		System.out.println("Base Damage " + baseDamage);
		
		float totalDamage = baseDamage * (1f -
				((float) (Math.log(defenderSheet.getStat(StatID.ARMOR_RATING).getTotal() + 1f)) /
				5));
		
//		System.out.println("1 - ln(Armor Rating + 1) / 5 = " + (1f -
//				((float) (Math.log(defenderSheet.getStat(StatID.ARMOR_RATING).getTotal() + 1f)) /
//				5)));
//		
//		System.out.println("Final Damage done: " + totalDamage);
//		System.out.println("");
		
		return totalDamage;
	}
	
}
