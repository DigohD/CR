package com.cr.item.stats.regen;

import java.awt.Color;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.StatsSheet;
import com.cr.item.stats.Stat;

public class LifeRegain extends Stat{

	private float amount;
	
	public LifeRegain(float amount) {
		super("Life Regain", new Color(180, 180, 180));
		this.amount = amount;
	}

	public void render(Screen screen, int xPos, int yPos) {
//		Font font = new Font("Tahoma", 14, 14);
//		g.setFont(font);
//		
//		String statS = String.format("%.1f", amount);
//		g.setColor(Color.BLACK);
//		g.drawString("" + name + ": " + statS, xPos - 1, yPos - 1);
//		g.setColor(color);
//		g.drawString("" + name + ": " + statS, xPos, yPos);
	}

	@Override
	public void applyToSheet() {
		StatsSheet.addArmor(amount);
	}

	@Override
	public void addAmount(float amount) {
		this.amount = this.amount + amount;
	}
	
	@Override
	public float getAmount() {
		return amount;
	}

}
