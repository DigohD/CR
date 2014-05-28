package com.cr.entity.hero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.cr.game.Game;

public class StatsSheet {

	private static int armor;
	private static int rHDamageBase, rHDamageDice;
	private static int lHDamageBase, lHDamageDice;
	private static int maxHP = 15;
	
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
		
		g.setColor(Color.WHITE);
		Font statFont = new Font("Tahoma", 14, 14);
		Font headerFont = new Font("Tahoma", 24, 24);
		Font sHeaderFont = new Font("Tahoma", 18, 18);
		
		g.setFont(headerFont);
		g.drawString("Defences", xOffset + 20, yOffset + 60);
		
		g.setFont(statFont);
		g.drawString("Maximum HP: ", xOffset + 20, yOffset + 100);
		g.drawString("" + maxHP, xOffset + 170, yOffset + 100);
		g.drawString("Armor: ", xOffset + 20, yOffset + 120);
		g.drawString("" + armor, xOffset + 170, yOffset + 120);
		
		g.setFont(headerFont);
		g.drawString("Attacks", xOffset + 320, yOffset + 60);
		g.setFont(sHeaderFont);
		if(Hero.getRightHand().getItem() != null){
			g.drawString("Right Hand", xOffset + 320, yOffset + 100);
			Hero.getRightHand().getItem().getStats()
					.render(g, xOffset + 320, yOffset + 120);
		}
		if(Hero.getLeftHand().getItem() != null){
			g.setFont(sHeaderFont);
			g.drawString("Left Hand", xOffset + 320, yOffset + 300);
			Hero.getLeftHand().getItem().getStats()
					.render(g, xOffset + 320, yOffset + 320);
		}
		
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
