package com.cr.item.stats.basic;

import java.awt.Color;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.StatsSheet;
import com.cr.item.stats.Stat;

public class BasicStat extends Stat{

	private float amount;
	public enum StatType {STRENGTH, DEXTERITY, INTELLECT, ENDURANCE};
	
	private StatType type;
	
	public BasicStat(float amount, StatType type) {
		super("", new Color(180, 180, 180));
		this.amount = amount;
		this.type = type;
		
		switch(type){
			case STRENGTH:
				name = "Strength";
				break;
			case DEXTERITY:
				name = "Dexterity";
				break;
			case INTELLECT:
				name = "Intellect";
				break;
			case ENDURANCE:
				name = "Endurance";
				break;
		}
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
		switch(type){
			case STRENGTH:
				StatsSheet.addStrength(amount);
				break;
			case DEXTERITY:
				StatsSheet.addDexterity(amount);
				break;
			case INTELLECT:
				StatsSheet.addIntellect(amount);
				break;
			case ENDURANCE:
				StatsSheet.addEndurance(amount);
				break;
		}
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
