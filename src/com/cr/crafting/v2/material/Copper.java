package com.cr.crafting.v2.material;

import java.awt.Image;
import java.util.ArrayList;

import com.cr.crafting.v2.property.Fragile;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.engine.graphics.Sprite;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.util.ImageLoader;

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
		return new Sprite("copper");
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	protected void newMods() {
		mod1 = Math.abs(1f + usedAmount / 50.0f);
		mod2 = Math.abs(1f - usedAmount / 50.0f);
		mod3 = Math.abs(1f + usedAmount / 25.0f);
		mod4 = Math.abs(1f - usedAmount / 35.0f);
		
		System.out.println(mod1 + " . " + mod2 + " . " + mod3 + " . " + mod4);
	}

	@Override
	public ArrayList<Stat> getWeaponStats(ArrayList<Stat> stats) {
		int span = 1;
//		if(state == State.BALANCED){
			span = (int) (span * mod3 * mod2 * 2);
			stats.add(new Damage(mod1, mod1 + span));
			stats.add(new CoolDown(60 * mod1 * mod2 * mod3 * mod4));
//		}
		return stats;
	}

	@Override
	public ArrayList<Stat> getArmorStats(ArrayList<Stat> stats) {
		return null;
	}
	
}
