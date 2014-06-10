package com.cr.crafting.pattern;

import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.Item;
import com.cr.item.armor.head.CopperHelm;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.Armor;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.CopperKnife;
import com.cr.item.weapon.Weapon;

public class HelmPattern extends Pattern{
	
	public HelmPattern(){
		super("helmpattern");
	}
	
	public void startNew(){
		item = new CopperHelm();
		bases.clear();
		secs.clear();
		secsAmount.clear();
		baseMaterial = null;
		baseAmount = 0;
		
		bases.add(Base.COPPER);
	}
	
	public void applyMaterial(Material material, int amount){
		material.affectBase(baseMaterial, amount);
		
		for(Material sec : secs)
			sec.affectSec(material, amount);
		
		secs.add(material);
		secsAmount.add(amount);
	}
	
	public void applyBaseMaterial(Material material, int amount){
		baseMaterial = material;
		if(material instanceof Copper){
			baseMaterial.getCurve().addPerMod(0.0f);
			baseMaterial.getCurve().addAmpMod(-0.3f);
			baseMaterial.getCurve().addOffset(-0.3f);
			baseAmount = amount;
		}
	}
	
	public CopperHelm generateItem(){
		float armor = (float) (baseMaterial.getCurve().getFunctionValue(baseAmount));
		item.addStat(new Armor(Math.abs(armor)));
		
		for(int i = 0; i < secs.size(); i++){
			Stat newStat = secs.get(i).getDefStat(secsAmount.get(i));
			for(Stat stat : item.getStats().getStats())
				if(stat.getName().equals(newStat.getName())){
					newStat.setDuplicate(true);
					stat.addAmount(newStat.getAmount());
				}
			
			if(!newStat.isDuplicate())
				item.addStat(newStat);
		}
			
		return (CopperHelm) item;
	}

	@Override
	public String getName() {
		return "Helm";
	}
	
}
