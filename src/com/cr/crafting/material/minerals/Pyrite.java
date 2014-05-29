package com.cr.crafting.material.minerals;

import com.cr.crafting.Curve;
import com.cr.crafting.material.Material;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.FlatDamage;

public class Pyrite extends Material{
	
	private class PyriteOffCurve extends Curve{
		@Override
		public float getFunctionValue(float x){
			float first = (float) (0.3f*ampMod*Math.sin(x*5f*perMod));
			return first;
		}
	}
	
	private PyriteOffCurve curve = new PyriteOffCurve();

	@Override
	public Curve getCurve() {
		return curve;
	}

	@Override
	public void affectBase(Material material, float amount) {
		material.getCurve().addAmpMod(curve.getFunctionValue(amount));
		material.getCurve().addPerMod(curve.getFunctionValue(amount*2.5f));
	}

	@Override
	public void affectSec(Material material, float amount) {
		material.getCurve().addAmpMod(curve.getFunctionValue(amount));
		material.getCurve().addPerMod(curve.getFunctionValue(amount*1.5f));
	}

	@Override
	public Stat getStat(float amount) {
		return new FlatDamage(Math.abs(curve.getFunctionValue(amount)) * 3);
	}

}
