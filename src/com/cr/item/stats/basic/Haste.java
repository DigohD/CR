package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.StatsSheet;
import com.cr.item.stats.Stat;

public class Haste extends Stat{

	private float base;
	
	public Haste(float base) {
		super("Speed Bonus", new Color(180, 180, 180));
		this.base = base;
	}

	@Override
	public void applyToSheet() {
		StatsSheet.addHaste(base);
	}
	
	@Override
	public void addAmount(float amount) {
		this.base = this.base + amount;
	}
	
	@Override
	public float getAmount() {
		return base;
	}

	@Override
	public void render(Screen screen, int xPos, int yPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modAmount(float amount) {
		// TODO Auto-generated method stub
	}
}
