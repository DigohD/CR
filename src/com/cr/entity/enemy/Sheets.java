package com.cr.entity.enemy;

import com.cr.entity.StatsSheet;

public class Sheets {

	public static StatsSheet dummySheet(){
		StatsSheet sheet = new StatsSheet();
		
		sheet.addMaxHP(10);
		sheet.addArmor(5);
		
		return sheet;
	}
	
}
