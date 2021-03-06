package com.cr.crafting.v2.pattern.armor;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.Hero;
import com.cr.game.Game;
import com.cr.item.Item;
import com.cr.item.armor.head.CopperHelm;
import com.cr.item.armor.lowerbody.CopperLeggings;
import com.cr.item.armor.upperbody.CopperPlate;
import com.cr.item.weapon.CopperKnife;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class LeggingsPattern extends Pattern{

	protected float ArmorMod;
	
	public LeggingsPattern(){
		super(true);
		ArmorMod = 0.8f;
		isWeapon = false;
	}

	@Override
	public Item generateItem(ArrayList<Material> materials){
		CopperLeggings ch = new CopperLeggings();
		createStatsFromMaterials(materials);
		for(StatMod x : stats){
			if(x.getAffectedStat() == StatID.ARMOR){
				x.mulAmount(ArmorMod);
			}
			x.setSourceID("lowerbody");
			ch.addStat(x);
		}
		applyQualities(ch, materials, "lowerbody");
		return ch;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("leggingspattern", Game.shader, new Transform());
	}
	
}
