package com.cr.crafting.v2.test;

import com.cr.crafting.v2.material.Copper;
import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.*;
import com.cr.crafting.v2.station.Forge;

public class CraftTest {

	public void craftTest(){
		Forge f = new Forge();
		f.setPattern(new KnifePattern());
		f.setHeat(800);
		f.setTime(150);
		f.addMaterial(new Copper());
		f.process();
		f.craft();
	}
	
}
