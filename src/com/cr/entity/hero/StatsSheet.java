package com.cr.entity.hero;

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
		maxHP = 0;
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
