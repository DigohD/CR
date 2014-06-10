package com.cr.item.stats;

import java.util.ArrayList;

import com.cr.engine.graphics.Screen;

public class StatsList{

	private ArrayList<Stat> stats = new ArrayList<Stat>();
	
	public void addStat(Stat stat){
		stats.add(stat);
	}
	
	public void render(Screen screen, int xOff, int yOff){
		for(Stat stat : stats){
			stat.render(screen, xOff, yOff);
			yOff = yOff + 20;
		}
	}
	
	public void applyStats(){
		for(Stat stat : stats)
			stat.applyToSheet();
	}

	public ArrayList<Stat> getStats() {
		return stats;
	}
	
	
}
