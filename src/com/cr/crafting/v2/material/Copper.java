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

	public Copper(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 500;
		higherHeatLimit = 1100;
		lowerTimeLimit = 30;
		higherTimeLimit = 300;
		balancedValue = 50;
		
		isPrimary = true;
		
		calculateMids();
		
		amount = 1;
		
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
	
}
