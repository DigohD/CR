package com.cr.crafting.material.essences;

import com.cr.crafting.Curve;
import com.cr.crafting.material.Material;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.BasicStat;
import com.cr.item.stats.basic.BasicStat.StatType;
import com.cr.item.stats.basic.FlatDamage;
import com.cr.item.stats.basic.SpeedBonus;
import com.cr.util.Randomizer;

public class StrangePowder extends Material{
	
	private class PowderOffCurve extends Curve{
		@Override
		public float getFunctionValue(float x){
			float first = (float) (1.75f*Math.sin(x));
			float second = (float) Math.log(x);
			float third = 0.5f;
			return first + second + third;
		}
	}
	
	private PowderOffCurve curve = new PowderOffCurve();

	@Override
	public Curve getCurve() {
		return curve;
	}

	@Override
	public void affectBase(Material material, float amount) {
		material.getCurve().addAmpMod(-0.1f);
		material.getCurve().addPerMod(0.2f);
	}

	@Override
	public void affectSec(Material material, float amount) {
		material.getCurve().addAmpMod(0.05f);
		material.getCurve().addPerMod(0);
	}

	@Override
	public Stat getOffStat(float amount) {
		switch(Randomizer.getInt2(0, 3)){
			case 0:
				return new BasicStat((int) curve.getFunctionValue(amount), StatType.STRENGTH);
			case 1:
				return new BasicStat((int) curve.getFunctionValue(amount), StatType.DEXTERITY);
			case 2:
				return new BasicStat((int) curve.getFunctionValue(amount), StatType.INTELLECT);
			case 3:
				return new BasicStat((int) curve.getFunctionValue(amount), StatType.ENDURANCE);
		}
		return null;
	}

	@Override
	public Stat getDefStat(float amount) {
		return null;
	}

}
