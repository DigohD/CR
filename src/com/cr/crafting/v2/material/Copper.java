package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.combat.Damage;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.game.Game;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class Copper extends Material{

	private float mod1, mod2, mod3, mod4;
	
	public Copper(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 500;
		higherHeatLimit = 1100;
		lowerTimeLimit = 30;
		higherTimeLimit = 300;
		balancedValue = 50;
		
		mod1 = 1f;
		mod2 = 1f;
		mod3 = 1f;
		mod4 = 1f;
		
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
		mod1 = 1f + usedAmount / 75.0f;
		mod2 = 1f + usedAmount / 65.0f;
		mod3 = 1f + usedAmount / 60.0f;
		mod4 = 1f + usedAmount / 55.0f;
		
		System.out.println(mod1 + " . " + mod2 + " . " + mod3 + " . " + mod4);
	}

	@Override
	public ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats) {
		int span = 1;

		StatMod damageMod;
		StatMod diceMod;
		StatMod cooldownMod;
		
		if(state == State.BALANCED){
			span = (int) (span * mod3 * mod2 * 2);
			
			damageMod = new StatMod(mod1, StatID.DAMAGE_BASE, "weapon", true);
			diceMod = new StatMod(mod1 + span, StatID.DAMAGE_DICE, "weapon", true);
			cooldownMod = new StatMod(10 * mod3 * mod4, StatID.COOLDOWN, "weapon", true);
			
			damageMod.mulAmount(1.25f);
			diceMod.mulAmount(0.75f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.BLASTED){
			span = (int) (span * mod1 * mod2 * 1);
			
			damageMod = new StatMod(mod2 * mod4, StatID.DAMAGE_BASE, "weapon", true);
			diceMod = new StatMod((mod2 * mod3) + span, StatID.DAMAGE_DICE, "weapon", true);
			cooldownMod = new StatMod(10 * mod1 * mod3 * mod4, StatID.COOLDOWN, "weapon", true);
			
			damageMod.mulAmount(1.5f);
			diceMod.mulAmount(1f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.FLASHED){
			span = (int) (span * mod1 * mod4 * mod3 * 1);

			damageMod = new StatMod(mod1 * mod3, StatID.DAMAGE_BASE, "weapon", true);
			diceMod = new StatMod((mod1 * mod3) + span, StatID.DAMAGE_DICE, "weapon", true);
			cooldownMod = new StatMod(5 * mod2 * mod3 * mod4, StatID.COOLDOWN, "weapon", true);
			
			damageMod.mulAmount(1f);
			diceMod.mulAmount(0.5f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.HARDENED){
			span = (int) (span * mod1 * 2);

			damageMod = new StatMod(mod3, StatID.DAMAGE_BASE, "weapon", true);
			diceMod = new StatMod(mod3 + span, StatID.DAMAGE_DICE, "weapon", true);
			cooldownMod = new StatMod(10 * mod2 * mod4, StatID.COOLDOWN, "weapon", true);
			
			damageMod.mulAmount(1.35f);
			diceMod.mulAmount(0.85f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}else if(state == State.TEMPERED){
			span = (int) (span * mod4 * 2);

			damageMod = new StatMod(mod1, StatID.DAMAGE_BASE, "weapon", true);
			diceMod = new StatMod(mod1 + span, StatID.DAMAGE_DICE, "weapon", true);
			cooldownMod = new StatMod(10 * mod3 * mod2, StatID.COOLDOWN, "weapon", true);
			
			damageMod.mulAmount(1.15f);
			diceMod.mulAmount(0.85f);
			
			stats.add(damageMod);
			stats.add(diceMod);
			stats.add(cooldownMod);
		}
		
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {
		return null;
	}
	
}
