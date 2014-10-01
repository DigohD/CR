package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.combat.Damage;
import com.cr.crafting.v2.property.Flexible;
import com.cr.crafting.v2.property.Fragile;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Repellant;
import com.cr.crafting.v2.property.Solid;
import com.cr.crafting.v2.property.Sturdy;
import com.cr.crafting.v2.property.Vibrant;
import com.cr.crafting.v2.property.Volatile;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.game.Game;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class ForestSoul extends Material{

	private float mod1, mod2, mod3, mod4;
	
	public ForestSoul(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 650;
		higherHeatLimit = 900;
		lowerTimeLimit = 70;
		higherTimeLimit = 200;
		balancedValue = 25;
		
		mod1 = 1f;
		mod2 = 1f;
		mod3 = 1f;
		mod4 = 1f;
		
		isPrimary = false;
		
		calculateMids();
		
		amount = 100;
		
		breakable = true;
		
		properties.add(new Repellant());
		properties.add(new Volatile());
	}

	@Override
	public String getName() {
		return "Forest Soul";
	}

	@Override
	public Sprite getMaterialImage() {
		return new Sprite("forestsoul", Game.shader, new Transform());
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
			stats.add(new StatMod(4 * mod1 * mod4, StatID.LIFE_ON_HIT, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(2 * mod3 * mod3, StatID.STRENGTH, "", true));
			stats.add(new StatMod(2 * mod4 * mod1, StatID.LIFE_ON_HIT, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(2 * mod1 * mod3, StatID.LIFE_ON_HIT, "", true));
			stats.add(new StatMod(2 * mod3 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(2 * mod2 * mod2, StatID.AGILITY, "", true));
			stats.add(new StatMod(2 * mod3 * mod1, StatID.LIFE_ON_HIT, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(2 * mod4 * mod1, StatID.LIFE_ON_HIT, "", true));
			stats.add(new StatMod(2 * mod2 * mod4, StatID.INTELLIGENCE, "", true));
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		StatMod armorMod;
		
		if(state == State.BALANCED){
			stats.add(new StatMod(4 * mod1 * mod4, StatID.LIFE_REGEN, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(2 * mod3 * mod3, StatID.STRENGTH, "", true));
			stats.add(new StatMod(2 * mod4 * mod1, StatID.LIFE_REGEN, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(2 * mod1 * mod3, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(2 * mod3 * mod4, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(2 * mod2 * mod2, StatID.AGILITY, "", true));
			stats.add(new StatMod(2 * mod3 * mod1, StatID.LIFE_REGEN, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(2 * mod4 * mod1, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(2 * mod2 * mod4, StatID.INTELLIGENCE, "", true));
		}
		return null;
	}

	@Override
	protected void setQualityMods() {
		switch(quality){
		case SUPERB:
			primbonus = 1f;
			baseBonus = 1.2f;
			secBonus = 1.2f;
			break;
		case MASTERFUL:
			primbonus = 1f;
			baseBonus = 1.4f;
			secBonus = 1.4f;
			break;
		default:
			break;
	}
	}
	
}
