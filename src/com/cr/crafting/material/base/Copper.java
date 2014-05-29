package com.cr.crafting.material.base;

import com.cr.crafting.Curve;
import com.cr.crafting.material.Material;
import com.cr.item.stats.Stat;

public class Copper extends Material{
	
	private class CopperCurve extends Curve{
		@Override
		public float getFunctionValue(float x){
			float first = (float) (2.0f*ampMod*Math.sin(x/45f*perMod));
			float second = 1 * offset;
			float third = (float) (2.0f*ampMod*Math.sin(x*perMod));
			return first + second + third;
		}
	}
	
	private CopperCurve curve = new CopperCurve();

	@Override
	public Curve getCurve() {
		return curve;
	}

	@Override
	public void affectBase(Material material, float amount) {
		
	}

	@Override
	public void affectSec(Material material, float amount) {
		
	}

	@Override
	public Stat getOffStat(float amount) {
		return null;
	}

	@Override
	public Stat getDefStat(float amount) {
		return null;
	}

}
