package com.cr.crafting.material.minerals;

import com.cr.crafting.Curve;
import com.cr.crafting.material.Material;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.FlatDamage;
import com.cr.item.stats.basic.MaxHP;
import com.cr.item.stats.basic.SpeedBonus;

public class Quartz extends Material{
	
	private class QuartzOffCurve extends Curve{
		@Override
		public float getFunctionValue(float x){
			float first = (float) (2f*ampMod*Math.sin(x*2f*perMod));
			float second = (float) (1f*ampMod*Math.sin(x/15f*perMod));
			return first + second;
		}
	}
	
	private QuartzOffCurve curve = new QuartzOffCurve();

	@Override
	public Curve getCurve() {
		return curve;
	}

	@Override
	public void affectBase(Material material, float amount) {
		material.getCurve().addAmpMod(curve.getFunctionValue(amount/2));
		material.getCurve().addPerMod(curve.getFunctionValue(amount));
	}

	@Override
	public void affectSec(Material material, float amount) {
		material.getCurve().addAmpMod(curve.getFunctionValue(amount * 0.75f));
		material.getCurve().addPerMod(curve.getFunctionValue(amount*1.5f));
	}

	@Override
	public Stat getOffStat(float amount){
		return new SpeedBonus(Math.abs(curve.getFunctionValue(amount)));
	}

	@Override
	public Stat getDefStat(float amount) {
		return new MaxHP(Math.abs(curve.getFunctionValue(amount) * 4));
	}

}
