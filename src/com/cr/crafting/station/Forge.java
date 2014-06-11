package com.cr.crafting.station;

import com.cr.crafting.pattern.HammerPattern;
import com.cr.crafting.pattern.HelmPattern;
import com.cr.crafting.pattern.KnifePattern;
import com.cr.crafting.pattern.Pattern;

public class Forge extends CraftingStation{

	public Forge(){
		
	}

	@Override
	public Pattern[] getPatterns(){
		Pattern[] patterns = {new HammerPattern(), new KnifePattern(),
				new HelmPattern()};
		return patterns;
	}
	
}