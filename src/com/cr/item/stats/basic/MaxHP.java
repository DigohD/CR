package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.StatsSheet;
import com.cr.item.stats.Stat;

public class MaxHP extends Stat{

	private int amount;
	
	public MaxHP(int amount) {
		super("Maximum HP", new Color(180, 180, 180));
		this.amount = amount;
	}

	public void render(Graphics2D g, int xPos, int yPos) {
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		g.drawString("" + name + ": +" + amount, xPos - 1, yPos - 1);
		g.setColor(color);
		g.drawString("" + name + ": +" + amount, xPos, yPos);
	}

	@Override
	public void applyToSheet() {
		StatsSheet.addMaxHP(amount);
	}

}
