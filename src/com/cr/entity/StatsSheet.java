package com.cr.entity;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;

public class StatsSheet {

	protected float armor;
	protected float maxHP;
	protected float lifeRegain;
	protected float haste;
	protected float damageBase, damageDice;
	
	public StatsSheet(){
		armor = 0;
		maxHP = 15;
		haste = 0;
		lifeRegain = 0;
	}
	

	public static void render(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
//		g.setColor(Color.WHITE);
//		Font statFont = new Font("Tahoma", 14, 14);
//		Font headerFont = new Font("Tahoma", 24, 24);
//		Font sHeaderFont = new Font("Tahoma", 18, 18);
//		
//		g.setFont(headerFont);
//		g.drawString("Base Statistics", xOffset + 20, yOffset + 60);
//		
//		g.setFont(statFont);
//		String statS = String.format("%.1f", strength);
//		g.drawString("Strength: ", xOffset + 20, yOffset + 100);
//		g.drawString("" + statS, xOffset + 170, yOffset + 100);
//		
//		statS = String.format("%.1f", dexterity);
//		g.drawString("Dexterity: ", xOffset + 20, yOffset + 120);
//		g.drawString("" + statS, xOffset + 170, yOffset + 120);
//		
//		statS = String.format("%.1f", intellect);
//		g.drawString("Intellect: ", xOffset + 20, yOffset + 140);
//		g.drawString("" + statS, xOffset + 170, yOffset + 140);
//		
//		statS = String.format("%.1f", endurance);
//		g.drawString("Endurance: ", xOffset + 20, yOffset + 160);
//		g.drawString("" + endurance, xOffset + 170, yOffset + 160);
//		
//		g.setFont(headerFont);
//		g.drawString("Defences", xOffset + 20, yOffset + 200);
//		
//		g.setFont(statFont);
//		statS = String.format("%.1f", maxHP);
//		g.drawString("Maximum HP: ", xOffset + 20, yOffset + 220);
//		g.drawString("" + statS, xOffset + 170, yOffset + 220);
//		statS = String.format("%.1f", armor);
//		g.drawString("Armor: ", xOffset + 20, yOffset + 240);
//		g.drawString("" + statS, xOffset + 170, yOffset + 240);
//		

	}
	
	
	public void addArmor(float amount){
		armor = armor + amount;
	}
	
	public void addMaxHP(float amount){
		maxHP = maxHP + amount;
	}

	public void addHaste(float amount){
		haste = haste + amount;
	}
	
	public float getArmor() {
		return armor;
	}

	public float getMaxHP() {
		return maxHP;
	}
	
	public float getHaste(){
		return haste;
	}
	
}
