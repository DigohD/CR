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
	public ArrayList<Stat> generateStat(boolean isWeapon){
		ArrayList<Stat> stats = new ArrayList<Stat>();
		if(isWeapon){
			stats.add(new Damage(3, 5));
			stats.add(new CoolDown(30));
			return stats;
		}
		return stats;
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
		mod1 = 1f + usedAmount / 50;
		mod2 = 1f - usedAmount / 50;
		mod3 = 1f + usedAmount / 25;
		mod4 = 1f - usedAmount / 35;
		
		System.out.println(mod1 + " . " + mod2 + " . " + mod3 + " . " + mod4);
	}
	
}
