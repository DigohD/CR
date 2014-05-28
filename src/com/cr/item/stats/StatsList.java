package com.cr.item.stats;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class StatsList{

	private ArrayList<Stat> stats = new ArrayList<Stat>();
	
	public void addStat(Stat stat){
		stats.add(stat);
	}
	
	public void render(Graphics2D g, int xOff, int yOff){
		for(Stat stat : stats){
			stat.render(g, xOff, yOff);
			yOff = yOff + 20;
		}
	}
}
