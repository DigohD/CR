package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.item.stats.Stat;

public class SpeedBonus extends Stat{

	private float base;
	
	public SpeedBonus(float base) {
		super("Speed Bonus", new Color(180, 180, 180));
		this.base = base;
	}

	@Override
	public void render(Graphics2D g, int xPos, int yPos) {
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		String baseS = String.format("%.1f", base);
		
		g.setColor(Color.BLACK);
		g.drawString("" + name + ": " + baseS, 
				xPos - 1, yPos - 1);
		g.setColor(color);
		g.drawString("" + name + ": " + baseS, 
				xPos, yPos);
	}

	@Override
	public void applyToSheet() {
		
	}
	
	@Override
	public void addAmount(float amount) {
		this.base = this.base + amount;
	}
	
	@Override
	public float getAmount() {
		return base;
	}
}
