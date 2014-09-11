package com.cr.entity.hero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.game.Game;

public class StatsSheet {

	private static float armor;
	private static float maxHP = 15;
	private static float strength, dexterity, intellect, endurance;
	private static float lifeRegain;
	private static float haste;
	
	public static void cleanseSheet(){
		armor = 0;
		
		maxHP = 15;
		
		strength = 10;
		dexterity = 10;
		intellect = 10;
		endurance = 10;
		
		haste = 0;
		
		lifeRegain = 0;
	}
	
	public static void render(Graphics2D g){
//		int xOffset = (Game.WIDTH - 800) / 2;
//		int yOffset = (Game.HEIGHT - 600) / 2;
//		
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
//		statS = String.format("%.1f", haste);
//		g.drawString("Haste: ", xOffset + 20, yOffset + 280);
//		g.drawString("" + statS, xOffset + 170, yOffset + 280);
		
//		g.setFont(headerFont);
//		g.drawString("Attacks", xOffset + 320, yOffset + 60);
	}
	
	public static void addArmor(float amount){
		armor = armor + amount;
	}
	
	public static void addMaxHP(float amount){
		maxHP = maxHP + amount;
	}
	
	public static void addStrength(float amount){
		strength = strength + amount;
	}
	
	public static void addDexterity(float amount){
		dexterity = dexterity + amount;
	}
	
	public static void addIntellect(float amount){
		intellect = intellect + amount;
	}
	
	public static void addEndurance(float amount){
		endurance = endurance + amount;
	}

	public static void addHaste(float amount){
		haste = haste + amount;
	}
	
	public static float getArmor() {
		return armor;
	}

	public static float getMaxHP() {
		return maxHP;
	}

	public static float getStrength() {
		return strength;
	}

	public static float getDexterity() {
		return dexterity;
	}

	public static float getIntellect() {
		return intellect;
	}

	public static float getEndurance() {
		return endurance;
	}
	
	public static float getHaste(){
		return haste;
	}
	
}
