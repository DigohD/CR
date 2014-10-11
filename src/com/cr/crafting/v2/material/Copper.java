package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.game.Game;
import com.cr.item.Item;
import com.cr.item.weapon.Weapon;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class Copper extends Material{
	
	public Copper(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 500;
		higherHeatLimit = 1100;
		lowerTimeLimit = 30;
		higherTimeLimit = 300;
		balancedValue = 50;
		
		isPrimary = true;
		
		calculateMids();
		
		amount = 100;
		
		breakable = true;
		
		properties.add(new Solid());
	}

	@Override
	public String getName() {
		return "Copper";
	}

	@Override
	public Sprite getMaterialImage() {
		return new Sprite("copper", Game.shader, new Transform());
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	protected void newMods() {
		
	}

	@Override
	public ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats) {
		int span = 1;
		
		StatMod damageMod;
		StatMod diceMod;
		StatMod cooldownMod;
		
		if(state == State.BALANCED){
			span = (int) (span * 2);
			
			damageMod = new StatMod(usedAmount/10 * primbonus, StatID.DAMAGE_BASE, "", true);
			diceMod = new StatMod(primbonus * span, StatID.DAMAGE_DICE, "", true);
			cooldownMod = new StatMod(usedAmount/10 * 10, StatID.COOLDOWN, "", true);
			
			damageMod.mulAmount(1.25f);
			diceMod.mulAmount(1.25f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.BLASTED){
			span = (int) (span * 2);
			
			damageMod = new StatMod(usedAmount/10 * primbonus, StatID.DAMAGE_BASE, "", true);
			diceMod = new StatMod(primbonus * span, StatID.DAMAGE_DICE, "", true);
			cooldownMod = new StatMod(usedAmount/10 * 10, StatID.COOLDOWN, "", true);
			
			damageMod.mulAmount(1.5f);
			diceMod.mulAmount(1f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.FLASHED){
			span = (int) (span * 2);

			damageMod = new StatMod(usedAmount/10 * primbonus, StatID.DAMAGE_BASE, "", true);
			diceMod = new StatMod(primbonus * span, StatID.DAMAGE_DICE, "", true);
			cooldownMod = new StatMod(usedAmount/10 * 10, StatID.COOLDOWN, "", true);
			
			damageMod.mulAmount(1f);
			diceMod.mulAmount(1.5f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.HARDENED){
			span = (int) (span * 2);

			damageMod = new StatMod(usedAmount/10 * primbonus, StatID.DAMAGE_BASE, "", true);
			diceMod = new StatMod(primbonus * span, StatID.DAMAGE_DICE, "", true);
			cooldownMod = new StatMod(usedAmount/10 * 10, StatID.COOLDOWN, "", true);
			
			damageMod.mulAmount(1.15f);
			diceMod.mulAmount(1.35f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.TEMPERED){
			span = (int) (span * 2);

			damageMod = new StatMod(usedAmount/10 * primbonus, StatID.DAMAGE_BASE, "", true);
			diceMod = new StatMod(primbonus * span, StatID.DAMAGE_DICE, "", true);
			cooldownMod = new StatMod(usedAmount/10 * 10, StatID.COOLDOWN, "", true);
			
			damageMod.mulAmount(1.35f);
			diceMod.mulAmount(1.15f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		StatMod armorMod;
		
		float base = 1;
		
		if(state == State.BALANCED){
			armorMod = new StatMod(usedAmount/10 * base * primbonus, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.BLASTED){
			armorMod = new StatMod(usedAmount/10 * base * primbonus, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.FLASHED){
			armorMod = new StatMod(usedAmount/10 * base * primbonus, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.HARDENED){
			armorMod = new StatMod(usedAmount/10 * base * primbonus, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.TEMPERED){
			armorMod = new StatMod(usedAmount/10 * base * primbonus, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}
		
		System.out.println("CHECK");
		System.out.println(usedAmount + " : " + base + " : " + primbonus);
		System.out.println(usedAmount/10 * base * primbonus);
		return stats;
	}

	@Override
	protected void setQualityMods() {
		switch(quality){
			case SUPERB:
				primbonus = 1.25f;
				baseBonus = 1f;
				secBonus = 1f;
				break;
			case MASTERFUL:
				primbonus = 1.5f;
				baseBonus = 1.25f;
				secBonus = 1f;
				break;
			case LEGENDARY:
				primbonus = 1.5f;
				baseBonus = 1.25f;
				secBonus = 1f;
				break;
			default:
				break;
		}
	}

	@Override
	public void applyQualityBonuses(Item i, String sourceID) {
		if(i instanceof Weapon)
			switch(quality){
				case SUPERB:
					break;
				case MASTERFUL:
					break;
				case LEGENDARY:
					i.addStat(new StatMod(75, StatID.PHYSICAL_POWER, sourceID, true));
					break;
				default:
					break;
			}
		else
			switch(quality){
				case SUPERB:
					break;
				case MASTERFUL:
					break;
				case LEGENDARY:
					i.addStat(new StatMod(10, StatID.ARMOR_RATING, sourceID, true));
					break;
				default:
					break;
		}
	}
	
}
