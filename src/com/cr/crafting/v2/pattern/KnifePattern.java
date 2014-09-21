package com.cr.crafting.v2.pattern;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.game.Game;
import com.cr.item.Item;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
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
		for(Stat x : stats){
			if(x instanceof CoolDown)
				((CoolDown) x).modAmount(ASMod);
			else if(x instanceof Damage)
				((Damage) x).modAmount(damageMod);
			ck.addStat(x);
			
			System.out.println(x.getName() + ": " + x.getAmount());
		}
		return ck;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("knifepattern", Game.shader, new Transform());
	}
	
}
