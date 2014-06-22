package com.cr.item.stats.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.combat.Damage.DamageType;
import com.cr.combat.DamagePacket;
import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.HeroSheet;
import com.cr.item.stats.AffectsDamageTaken;
import com.cr.item.stats.Stat;

public class Armor extends Stat implements AffectsDamageTaken{

	private float amount;
	
	public Armor(float amount) {
		super("Armor", new Color(180, 180, 180));
		this.amount = amount;
	}

	public void render(Graphics2D g, int xPos, int yPos) {
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
		HeroSheet.addArmor(amount);
	}

	@Override
	public void addAmount(float amount) {
		this.amount = this.amount + amount;
	}
	
	@Override
	public float getAmount() {
		return amount;
	}


	public void render(Screen screen, int xPos, int yPos) {
		// TODO Auto-generated method stub
	}

	public void affectDamage(DamagePacket packet) {
		for(com.cr.combat.Damage x : packet.getDmgs())
			if(x.getType() == DamageType.PHYSICAL){
				x.addDamageBase(-amount / 4);
				x.addDamageDice(-amount / 4);
			}
	}

}
