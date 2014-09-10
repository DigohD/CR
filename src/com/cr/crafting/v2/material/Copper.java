package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.crafting.v2.property.Fragile;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.Damage;

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
		
		breakable = true;
		
		properties.add(new Solid());
	}

	@Override
	public String getName() {
		return "Copper";
	}

	@Override
	public Stat generateStat(boolean isWeapon) {
		if(isWeapon){
			return new Damage(3, 5);
		}
		return null;
	}
	
}
