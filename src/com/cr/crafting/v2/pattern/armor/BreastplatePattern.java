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
import com.cr.item.armor.upperbody.CopperPlate;
import com.cr.item.weapon.CopperKnife;
import com.cr.stats.StatMod;
import com.cr.stats.StatsSheet.StatID;

public class BreastplatePattern extends Pattern{

	protected float ArmorMod;
	
	public BreastplatePattern(){
		super(true);
		ArmorMod = 1.2f;
		isWeapon = false;
	}

	@Override
	public Item generateItem(ArrayList<Material> materials){
		CopperPlate ch = new CopperPlate();
		createStatsFromMaterials(materials);
		for(StatMod x : stats){
			if(x.getAffectedStat() == StatID.ARMOR){
				x.mulAmount(ArmorMod);
			}
			x.setSourceID("upperbody");
			ch.addStat(x);
		}
		applyQualities(ch, materials, "upperbody");
		return ch;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite("breastplatepattern", Game.shader, new Transform());
	}
	
}
