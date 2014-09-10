package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;

class KnifePattern extends Pattern{

	public KnifePattern(){
		ASMod = 1.5f;
		damageMod = 0.5f;
	}

	@Override
	public void createStatsFromMaterials(ArrayList<Material> materials) {
		
	}
	
}
