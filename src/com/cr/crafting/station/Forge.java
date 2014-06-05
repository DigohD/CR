package com.cr.crafting.station;

import com.cr.crafting.pattern.HammerPattern;
import com.cr.crafting.pattern.Pattern;

public class Forge extends CraftingStation{

	public Forge(){
		
	}

	@Override
	public Pattern[] getPatterns(){
		Pattern[] patterns = {new HammerPattern()};
		return patterns;
	}
	
}
