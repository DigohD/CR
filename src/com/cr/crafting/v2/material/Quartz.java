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
import com.cr.item.Item;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class Quartz extends Material{
	public Quartz(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 400;
		higherHeatLimit = 1000;
		lowerTimeLimit = 15;
		higherTimeLimit = 250;
		balancedValue = 45;
		
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
		return 3;
	}

	@Override
	protected void newMods() {
		
	}

	@Override
	public ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats) {
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 3 * secBonus, StatID.MANA_MAX, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 9 * secBonus, StatID.MANA_MAX, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(usedAmount/10 * 3 * baseBonus, StatID.SPELL_POWER, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 6 * baseBonus, StatID.SPELL_POWER, "", true));
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 3 * secBonus, StatID.MANA_MAX, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.INTELLIGENCE, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 9 * secBonus, StatID.MANA_MAX, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.INTELLIGENCE, "", true));
			stats.add(new StatMod(usedAmount/10 * 3 * baseBonus, StatID.SPELL_POWER, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 6 * baseBonus, StatID.SPELL_POWER, "", true));
		}
		return stats;
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
		case LEGENDARY:
			primbonus = 1f;
			baseBonus = 1.5f;
			secBonus = 1f;
			break;
		default:
			break;
	}
	}

	@Override
	public void applyQualityBonuses(Item i, String sourceID) {
		// TODO Auto-generated method stub
		
	}
	
}
