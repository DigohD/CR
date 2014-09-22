package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.Hero;
import com.cr.game.Game;
import com.cr.item.Item;
import com.cr.item.weapon.CopperKnife;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class KnifePattern extends Pattern{

	protected float ASMod, damageMod;
	
	public KnifePattern(){
		super(true);
		ASMod = 0.5f;
		damageMod = 0.5f;
	}

	@Override
	public Item generateItem(){
		CopperKnife ck = new CopperKnife();
		for(StatMod x : stats){
			System.out.println("Stat applying: " + x.getAffectedStat() + " : " + x.getAmount());
			if(x.getAffectedStat() == StatID.DAMAGE_BASE)
				ck.getDamageBase().setNewBase(x.getAmount() * damageMod);
			else if(x.getAffectedStat() == StatID.DAMAGE_DICE)
				ck.getDamageDice().setNewBase(x.getAmount() * damageMod);
			else if(x.getAffectedStat() == StatID.COOLDOWN)
				ck.getCooldown().setNewBase(x.getAmount() * ASMod);
			else
				ck.addStat(x);
		}
		
		System.out.println(ck.getDamageBase().getTotal() + " - " + ck.getDamageDice().getTotal());
		System.out.println(ck.getCooldown().getTotal());
		
		return ck;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("knifepattern", Game.shader, new Transform());
	}
	
}
