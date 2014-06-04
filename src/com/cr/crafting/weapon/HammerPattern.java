package com.cr.crafting.weapon;

import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.CopperKnife;
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.Weapon;

public class HammerPattern {

	public static ArrayList<Base> bases = new ArrayList<Base>();
	
	private static Weapon knife;
	
	private static Material baseMaterial;
	private static int baseAmount;
	
	private static ArrayList<Material> secs = new ArrayList<Material>();
	private static ArrayList<Integer> secsAmount = new ArrayList<Integer>();
	
	public HammerPattern(){
		bases.add(Base.COPPER);
	}
	
	public static void startNew(){
		knife = new Hammer();
		secs.clear();
		secsAmount.clear();
		baseMaterial = null;
		baseAmount = 0;
	}
	
	public static void applyMaterial(Material material, int amount){
		material.affectBase(baseMaterial, amount);
		
		for(Material sec : secs)
			sec.affectSec(material, amount);
		
		secs.add(material);
		secsAmount.add(amount);
	}
	
	public static void applyBaseMaterial(Material material, int amount){
		baseMaterial = material;
		if(material instanceof Copper){
			baseMaterial.getCurve().addPerMod(0.5f);
			baseMaterial.getCurve().addAmpMod(0.3f);
			baseMaterial.getCurve().addOffset(-0.15f);
			baseAmount = amount;
		}
	}
	
	public static Weapon getHammer(){
		Damage dmg = new Damage(baseMaterial.getCurve().getFunctionValue(baseAmount),
				baseMaterial.getCurve().getFunctionValue(baseAmount));
		CoolDown cd = new CoolDown(180 + (baseAmount / 4));
		
		knife.addStat(dmg);
		knife.addStat(cd);
		
		for(int i = 0; i < secs.size(); i++){
			Stat newStat = secs.get(i).getOffStat(secsAmount.get(i));
			for(Stat stat : knife.getStats().getStats())
				if(stat.getName().equals(newStat.getName())){
					newStat.setDuplicate(true);
					stat.addAmount(newStat.getAmount());
				}
			
			if(!newStat.isDuplicate())
				knife.addStat(newStat);
		}
		return knife;
	}
	
}
