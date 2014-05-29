package com.cr.crafting.armor;

import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.armor.head.CopperHelm;
import com.cr.item.stats.basic.Armor;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.CopperKnife;
import com.cr.item.weapon.MeleeWeapon;

public class HelmPattern {

	public static ArrayList<Base> bases = new ArrayList<Base>();
	
	private static CopperHelm helm;
	
	private static Material baseMaterial;
	private static int baseAmount;
	
	private static ArrayList<Material> secs = new ArrayList<Material>();
	private static ArrayList<Integer> secsAmount = new ArrayList<Integer>();
	
	public HelmPattern(){
		bases.add(Base.COPPER);
	}
	
	public static void startNew(){
		helm = new CopperHelm();
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
			baseMaterial.getCurve().addPerMod(0.0f);
			baseMaterial.getCurve().addAmpMod(-0.3f);
			baseMaterial.getCurve().addOffset(-0.3f);
			baseAmount = amount;
		}
	}
	
	public static CopperHelm getKnife(){
		int armor = (int) (baseMaterial.getCurve().getFunctionValue(baseAmount) * 5);
		helm.addStat(new Armor(Math.abs(armor)));
		
		for(int i = 0; i < secs.size(); i++){
			helm.addStat(secs.get(i).getDefStat(secsAmount.get(i)));
		}
			
		return helm;
	}
	
}
