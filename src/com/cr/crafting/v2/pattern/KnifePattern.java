package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.Hero;
import com.cr.item.Item;
import com.cr.item.weapon.CopperKnife;
import com.cr.stats.StatMod;

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
		
		for(StatMod x : stats){
			if(x.getAffectedStat().equals("Damage_Base"))
				ck.getDamageBase().setNewBase(x.getAmount() * damageMod);
			else if(x.getAffectedStat().equals("Damage_Dice"))
				ck.getDamageDice().setNewBase(x.getAmount() * damageMod);
			else if(x.getAffectedStat().equals("Cooldown"))
				ck.getCooldown().setNewBase(x.getAmount() * ASMod);
			else
				Hero.getSheet().addMod(x);;
		}
		return ck;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("knifepattern");
	}
	
}
