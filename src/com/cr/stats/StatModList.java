package com.cr.stats;

import java.util.ArrayList;

public class StatModList {

	private ArrayList<StatMod> statMods = new ArrayList<StatMod>();
	
	public void addMod(StatMod mod){
		statMods.add(mod);
	}

	public ArrayList<StatMod> getStatMods() {
		return statMods;
	}
	
	
	
}
