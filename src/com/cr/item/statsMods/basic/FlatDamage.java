package com.cr.item.statsMods.basic;

import java.awt.Color;

import com.cr.combat.Damage.DamageType;
import com.cr.combat.DamagePacket;
import com.cr.engine.graphics.Screen;
import com.cr.item.statsMods.AddsDamageDone;
import com.cr.item.statsMods.Stat;

public class FlatDamage extends Stat implements AddsDamageDone{

	private float base;
	
	public FlatDamage(float base) {
		super("Sharpened Damage", new Color(180, 180, 180));
		this.base = base;
	}

	@Override
	public void render(Screen screen, int xPos, int yPos) {
//		Font font = new Font("Tahoma", 14, 14);
//		g.setFont(font);
//		
//		String baseS = String.format("%.1f", base);
//		
//		g.setColor(Color.BLACK);
//		g.drawString("" + name + ": +" + baseS, 
//				xPos - 1, yPos - 1);
//		g.setColor(color);
//		g.drawString("" + name + ": +" + baseS, 
//				xPos, yPos);
	}

	@Override
	public void affectDamage(DamagePacket packet) {
		packet.addDamage(new com.cr.combat.Damage(base, 0f, DamageType.PHYSICAL));
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

	@Override
	public void modAmount(float amount) {
		// TODO Auto-generated method stub
		
	}

	
}
