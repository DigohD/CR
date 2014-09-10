package com.cr.crafting.v2.pattern;

import com.cr.item.Item;
import com.cr.item.stats.Stat;
import com.cr.item.weapon.CopperKnife;

public class KnifePattern extends Pattern{

	protected float ASMod, damageMod;
	
	public KnifePattern(){
		super(true);
		ASMod = 1.5f;
		damageMod = 0.5f;
	}

	@Override
	public Item generateItem(){
		CopperKnife ck = new CopperKnife();
		for(Stat x : stats)
			ck.addStat(x);
		return ck;
	}
	
}
