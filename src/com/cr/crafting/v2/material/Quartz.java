package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.combat.Damage;
import com.cr.crafting.v2.property.Flexible;
import com.cr.crafting.v2.property.Fragile;
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

public class Quartz extends Material{

	private float mod1, mod2, mod3, mod4;
	
	public Quartz(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 400;
		higherHeatLimit = 1000;
		lowerTimeLimit = 15;
		higherTimeLimit = 250;
		balancedValue = 45;
		
		mod1 = 1f;
		mod2 = 1f;
		mod3 = 1f;
		mod4 = 1f;
		
		isPrimary = false;
		
		calculateMids();
		
		amount = 100;
		
		breakable = true;
		
		properties.add(new Fragile());
		properties.add(new Flexible());
	}

	@Override
	public String getName() {
		return "Quartz";
	}

	@Override
	public Sprite getMaterialImage() {
		return new Sprite("quartz", Game.shader, new Transform());
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
			stats.add(new StatMod(4 * mod2 * mod4 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(2 * mod4 * mod3 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(2 * mod2 * mod3 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(2 * mod1 * mod2 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(2 * mod3 * mod2 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(2 * mod2 * mod4 * baseBonus, StatID.AGILITY, "", true));
			stats.add(new StatMod(2 * mod3 * mod1 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(2 * mod3 * mod1 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(14 * mod2 * mod4 * baseBonus, StatID.HP_MAX, "", true));
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		StatMod armorMod;
		float base = 5;
		
		if(state == State.BALANCED){
			stats.add(new StatMod(4 * mod2 * mod4 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(2 * mod4 * mod3 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(2 * mod2 * mod3 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(2 * mod1 * mod2 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(2 * mod3 * mod2 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(2 * mod2 * mod4 * baseBonus, StatID.AGILITY, "", true));
			stats.add(new StatMod(2 * mod3 * mod1 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(2 * mod3 * mod1 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(14 * mod2 * mod4 * baseBonus, StatID.HP_MAX, "", true));
		}
		return null;
	}

	@Override
	protected void setQualityMods() {
		switch(quality){
		case SUPERB:
			primbonus = 1f;
			baseBonus = 1.25f;
			secBonus = 1f;
			break;
		case MASTERFUL:
			primbonus = 1f;
			baseBonus = 1.5f;
			secBonus = 1f;
			break;
		default:
			break;
	}
	}
	
}
