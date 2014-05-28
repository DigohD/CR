package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.item.stats.Stat;

public class Damage extends Stat{

	private int base, dice, diceValue;
	
	public Damage(int base, int dice, int diceValue) {
		super("Basic Damage", new Color(180, 180, 180));
		this.base = base;
		this.dice = dice;
		this.diceValue = diceValue;
	}

	@Override
	public void render(Graphics2D g, int xPos, int yPos) {
		int maximum = base + (dice * diceValue);
		
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		g.drawString("" + name + ": " + base + " - " + maximum, 
				xPos - 1, yPos - 1);
		g.setColor(color);
		g.drawString("" + name + ": " + base + " - " + maximum, 
				xPos, yPos);
	}

}
