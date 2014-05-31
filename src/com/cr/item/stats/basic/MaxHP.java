package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.StatsSheet;
import com.cr.item.stats.Stat;

public class MaxHP extends Stat{

	private float amount;
	
	public MaxHP(float amount) {
		super("Maximum HP", new Color(180, 180, 180));
		this.amount = amount;
	}

	public void render(Graphics2D g, int xPos, int yPos) {
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		String statS = String.format("%.1f", amount);
		g.setColor(Color.BLACK);
		g.drawString("" + name + ": +" + statS, xPos - 1, yPos - 1);
		g.setColor(color);
		g.drawString("" + name + ": +" + statS, xPos, yPos);
	}

	@Override
	public void applyToSheet() {
		StatsSheet.addMaxHP(amount);
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
