package com.cr.entity.hero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.game.Game;

public class StatsSheet {

	private static int armor;
	private static int rHDamageBase, rHDamageDice;
	private static int lHDamageBase, lHDamageDice;
	private static int maxHP;
	
	public static void cleanseSheet(){
		armor = 0;
		rHDamageBase = 0;
		rHDamageDice = 0;
		lHDamageBase = 0;
		lHDamageDice = 0;
		maxHP = 15;
	}
	
	public static void render(Graphics2D g){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		g.drawString("Maximum HP: ", xOffset + 20, yOffset + 100);
		g.drawString("" + maxHP, xOffset + 170, yOffset + 100);
		g.drawString("Armor: ", xOffset + 20, yOffset + 100);
		g.drawString("" + armor, xOffset + 170, yOffset + 100);
	}
	
	public static void addArmor(int amount){
		armor = armor + amount;
	}
	
	public static void addRHDamageBase(int amount){
		rHDamageBase = rHDamageBase + amount;
	}
	
	public static void addRHDamageDice(int amount){
		rHDamageDice = rHDamageDice + amount;
	}
	
	public static void addLHDamageBase(int amount){
		lHDamageBase = lHDamageBase + amount;
	}
	
	public static void addLHDamageDice(int amount){
		lHDamageDice = lHDamageDice + amount;
	}
	
	public static void addMaxHP(int amount){
		maxHP = maxHP + amount;
	}
}
