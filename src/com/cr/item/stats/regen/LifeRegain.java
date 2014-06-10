package com.cr.item.stats.regen;

import java.awt.Color;

import com.cr.engine.graphics.Screen;
import com.cr.entity.Mob;
import com.cr.item.stats.PassiveTicking;
import com.cr.item.stats.Stat;

public class LifeRegain extends Stat implements PassiveTicking{

	private float amount;
	private Mob owner;
	
	public LifeRegain(float amount, Mob owner) {
		super("Life Regain", new Color(180, 180, 180));
		this.amount = amount;
		this.owner = owner;
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
	public void tick(float dt) {
		owner.addHealth(amount / 60);
	}

}
