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
import com.cr.item.Item;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class ForestSoul extends Material{
	
	public ForestSoul(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 650;
		higherHeatLimit = 900;
		lowerTimeLimit = 70;
		higherTimeLimit = 200;
		balancedValue = 25;
		
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
		return 4;
	}

	@Override
	protected void newMods() {
		
	}

	@Override
	public ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats) {
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 4 * secBonus, StatID.LIFE_ON_HIT, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_ON_HIT, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_ON_HIT, "", true));
			stats.add(new StatMod(usedAmount/10 * 0.05f * secBonus, StatID.MOVEMENT_SPEED, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(usedAmount/10 * 0.05f * secBonus, StatID.MOVEMENT_SPEED, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_ON_HIT, "", true));
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.HP_MAX, "", true));
		}
		return stats;
	}

	@Override
	public ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats) {	
		if(state == State.BALANCED){
			stats.add(new StatMod(usedAmount/10 * 4 * secBonus, StatID.LIFE_REGEN, "", true));
		}else if(state == State.BLASTED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.ARMOR, "", true));
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.HP_MAX, "", true));
		}else if(state == State.FLASHED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(usedAmount/10 * 0.05f * baseBonus, StatID.MOVEMENT_SPEED, "", true));
		}else if(state == State.HARDENED){
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.HP_MAX, "", true));
			stats.add(new StatMod(usedAmount/10 * 0.05f * baseBonus, StatID.MOVEMENT_SPEED, "", true));
		}else if(state == State.TEMPERED){
			stats.add(new StatMod(usedAmount/10 * 2 * secBonus, StatID.LIFE_REGEN, "", true));
			stats.add(new StatMod(usedAmount/10 * 2 * baseBonus, StatID.HP_MAX, "", true));
		}
		return stats;
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
			case LEGENDARY:
				primbonus = 1f;
				baseBonus = 1.4f;
				secBonus = 1.4f;
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
