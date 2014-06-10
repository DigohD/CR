package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;

import com.cr.engine.graphics.Screen;
import com.cr.item.stats.AffectsDamage;
import com.cr.item.stats.Stat;
import com.cr.util.Randomizer;

public class Damage extends Stat implements AffectsDamage{

	private float base, dice;
	
	public Damage(float base, float dice) {
		super("Basic Damage", new Color(180, 180, 180));
		this.base = base;
		this.dice = dice;
	}

	@Override
	public void render(Screen screen, int xPos, int yPos) {
//		float maximum = base + dice;
//		
//		Font font = new Font("Tahoma", 14, 14);
//		g.setFont(font);
//		
//		String baseS = String.format("%.1f", base);
//		String maximumS = String.format("%.1f", maximum);
//		g.setColor(Color.BLACK);
//		g.drawString("" + name + ": " + baseS + " - " + maximumS, 
//				xPos - 1, yPos - 1);
//		g.setColor(color);
//		g.drawString("" + name + ": " + baseS + " - " + maximumS, 
//				xPos, yPos);
	}

	@Override
	public float affectDamage(float damage) {
		return damage + (Randomizer.getFloat(base, base + dice));
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

	public float getBase() {
		return base;
	}

	public float getDice() {
		return dice;
	}

	
	
	

}
