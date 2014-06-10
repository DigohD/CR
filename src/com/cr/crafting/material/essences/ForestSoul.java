package com.cr.crafting.material.essences;

import com.cr.crafting.Curve;
import com.cr.crafting.material.Material;
import com.cr.game.EntityManager;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.BasicStat;
import com.cr.item.stats.basic.BasicStat.StatType;
import com.cr.item.stats.basic.FlatDamage;
import com.cr.item.stats.regen.LifeOnHit;
import com.cr.item.stats.regen.LifeRegain;
import com.cr.util.Randomizer;

public class ForestSoul extends Material{
	
	private class FSOffCurve extends Curve{
		@Override
		public float getFunctionValue(float x){
			float first = (float) (1.5f*ampMod*Math.sin(x*perMod));
			float second = (float) ((float) 3*ampMod*Math.log(x*perMod));
			return first + second;
		}
	}
	
	private FSOffCurve curve = new FSOffCurve();

	@Override
	public Curve getCurve() {
		return curve;
	}

	@Override
	public void affectBase(Material material, float amount) {
		material.getCurve().addAmpMod(-0.05f);
		material.getCurve().addPerMod(0.1f);
	}

	@Override
	public void affectSec(Material material, float amount) {
		material.getCurve().addAmpMod(0.1f);
		material.getCurve().addPerMod(0.2f);
	}

	@Override
	public Stat getOffStat(float amount) {
		return new LifeOnHit(curve.getFunctionValue(amount) / 4);
	}

	@Override
	public Stat getDefStat(float amount){
		if(curve.getFunctionValue(amount) / 10 > 0)
			return new LifeRegain(curve.getFunctionValue(amount) / 8, EntityManager.getHero());
		else
			return new LifeRegain(1f, EntityManager.getHero());
	}

}
