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
import com.cr.item.weapon.CopperKnife;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class HelmPattern extends Pattern{

	protected float ArmorMod;
	
	public HelmPattern(){
		super(true);
		ArmorMod = 0.65f;
		isWeapon = false;
	}

	@Override
	public Item generateItem(ArrayList<Material> materials){
		CopperHelm ch = new CopperHelm();
		createStatsFromMaterials(materials);
		for(StatMod x : stats){
			if(x.getAffectedStat() == StatID.ARMOR){
				x.mulAmount(ArmorMod);
			}
			x.setSourceID("head");
			ch.addStat(x);
		}
		applyQualities(ch, materials, "head");
		return ch;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("helmpattern", Game.shader, new Transform());
	}
	
}
