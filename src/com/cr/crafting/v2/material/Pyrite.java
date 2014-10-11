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
import com.cr.item.Item;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class Pyrite extends Material{
	
	public Pyrite(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 600;
		higherHeatLimit = 1050;
		lowerTimeLimit = 60;
		higherTimeLimit = 280;
		balancedValue = 45;
		
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
		return 2;
	}

	@Override
	protected void newMods() {

	}

	@Override
	public ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats) {
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(usedAmount/10 * 3 * secBonus, StatID.PHYSICAL_POWER, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 3 * secBonus, StatID.PHYSICAL_POWER, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.STRENGTH, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.TOUGHNESS, "", true));
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * secBonus, StatID.ARMOR_RATING, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.STRENGTH, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 1 * secBonus, StatID.ARMOR_RATING, "", true));
			stats.add(new StatMod(usedAmount/10 * 1 * baseBonus, StatID.TOUGHNESS, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.STRENGTH, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.TOUGHNESS, "", true));
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
