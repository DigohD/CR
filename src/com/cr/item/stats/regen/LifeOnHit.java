package com.cr.item.stats.regen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.engine.graphics.Screen;
import com.cr.entity.Mob;
import com.cr.entity.hero.HeroSheet;
import com.cr.item.stats.AffectBearerOnHit;
import com.cr.item.stats.PassiveTicking;
import com.cr.item.stats.Stat;

public class LifeOnHit extends Stat implements AffectBearerOnHit{

	private float amount;
	
	public LifeOnHit(float amount) {
		super("Life On Hit", new Color(180, 180, 180));
		this.amount = amount;
	}

	public void render(Graphics2D g, int xPos, int yPos) {
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		String statS = String.format("%.1f", amount);
		g.setColor(Color.BLACK);
		g.drawString("" + name + ": " + statS, xPos - 1, yPos - 1);
		g.setColor(color);
		g.drawString("" + name + ": " + statS, xPos, yPos);
	}

	@Override
	public void applyToSheet() {
		
	}

	@Override
	public void addAmount(float amount) {
		this.amount = this.amount + amount;
	}
	
	@Override
	public float getAmount(){
		return amount;
	}

	@Override
	public void affectMob(Mob mob) {
		mob.addHealth(amount);
	}

	@Override
	public void render(Screen screen, int xPos, int yPos) {
		// TODO Auto-generated method stub
		
	}
}
