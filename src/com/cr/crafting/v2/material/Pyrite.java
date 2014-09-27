package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.combat.Damage;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.crafting.v2.property.Sturdy;
import com.cr.crafting.v2.property.Vibrant;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.game.Game;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class Pyrite extends Material{

	private float mod1, mod2, mod3, mod4;
	
	public Pyrite(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 600;
		higherHeatLimit = 1050;
		lowerTimeLimit = 60;
		higherTimeLimit = 280;
		balancedValue = 45;
		
		mod1 = 1f;
		mod2 = 1f;
		mod3 = 1f;
		mod4 = 1f;
		
		isPrimary = false;
		
		calculateMids();
		
		amount = 100;
		
		breakable = true;
		
		properties.add(new Sturdy());
		properties.add(new Vibrant());
	}

	@Override
	public String getName() {
		return "Pyrite";
	}

	@Override
	public Sprite getMaterialImage() {
		return new Sprite("pyrite", Game.shader, new Transform());
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
		if(state == State.BALANCED){
			stats.add(new StatMod(1 * mod1 * mod4, StatID.STRENGTH, "", true));
			stats.add(new StatMod(1 * mod2 * mod3, StatID.AGILITY, "", true));
			stats.add(new StatMod(1 * mod1 * mod3, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(1 * mod2 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(1 * mod1 * mod4, StatID.STRENGTH, "", true));
			stats.add(new StatMod(1 * mod2 * mod3, StatID.AGILITY, "", true));
			stats.add(new StatMod(1 * mod1 * mod3, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(1 * mod2 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(1 * mod1 * mod4, StatID.STRENGTH, "", true));
			stats.add(new StatMod(1 * mod2 * mod3, StatID.AGILITY, "", true));
			stats.add(new StatMod(1 * mod1 * mod3, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(1 * mod2 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(1 * mod1 * mod4, StatID.STRENGTH, "", true));
			stats.add(new StatMod(1 * mod2 * mod3, StatID.AGILITY, "", true));
			stats.add(new StatMod(1 * mod1 * mod3, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(1 * mod2 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(1 * mod1 * mod4, StatID.STRENGTH, "", true));
			stats.add(new StatMod(1 * mod2 * mod3, StatID.AGILITY, "", true));
			stats.add(new StatMod(1 * mod1 * mod3, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(1 * mod2 * mod4, StatID.TOUGHNESS, "", true));
		}
		
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		StatMod armorMod;
		
		float base = 5;
		
		if(state == State.BALANCED){
			armorMod = new StatMod(base * mod1 * mod4, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.BLASTED){
			armorMod = new StatMod(base * mod2 * mod4, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.FLASHED){
			armorMod = new StatMod(base * mod1 * mod3, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.HARDENED){
			armorMod = new StatMod(base * mod3 * mod2, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}else if(state == State.TEMPERED){
			armorMod = new StatMod(base * mod1 * mod2, StatID.ARMOR, "", true);
			stats.add(armorMod);
		}
		
		return null;
	}
	
}
