package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.crafting.v2.property.Fragile;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;

public class Copper extends Material{

	public Copper(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 500;
		higherHeatLimit = 1100;
		lowerTimeLimit = 30;
		higherTimeLimit = 300;
		balancedValue = 50;
		
		heatMidPoint = ((higherHeatLimit - lowerHeatLimit) / 2) + lowerHeatLimit;
		timeMidPoint = ((higherTimeLimit - lowerTimeLimit) / 2) + lowerTimeLimit;
		
		breakable = true;
		
		properties.add(new Solid());
	}

	@Override
	public String getName() {
		return "Copper";
	}
	
	
	
}
