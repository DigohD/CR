package com.cr.crafting.material;

import com.cr.crafting.Curve;
import com.cr.item.stats.Stat;

public abstract class Material{

	public abstract Curve getCurve();
	
	public abstract void affectBase(Material material, float amount);
	public abstract void affectSec(Material material, float amount);
	public abstract Stat getStat(float amount);
	
}
