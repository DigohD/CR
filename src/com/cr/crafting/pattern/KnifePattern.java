package com.cr.crafting.pattern;

import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.crafting.material.base.Copper;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.Item;
import com.cr.item.stats.Stat;
import com.cr.item.stats.basic.CoolDown;
import com.cr.item.stats.basic.Damage;
import com.cr.item.weapon.CopperKnife;
import com.cr.item.weapon.Weapon;

public class KnifePattern extends Pattern{
	
	public KnifePattern(){
		super("knifepattern");
	}
	
	@Override
	public void startNew(){
		item = new CopperKnife();
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
			baseMaterial.getCurve().addPerMod(0.2f);
			baseMaterial.getCurve().addAmpMod(-0.2f);
			baseMaterial.getCurve().addOffset(0.2f);
			baseAmount = amount;
		}
	}
	
	public Weapon generateItem(){
		Damage dmg = new Damage(baseMaterial.getCurve().getFunctionValue(baseAmount),
				baseMaterial.getCurve().getFunctionValue(baseAmount));
		CoolDown cd = new CoolDown(100 + (baseAmount / 6));
		
		item.addStat(dmg);
		item.addStat(cd);
		
		for(int i = 0; i < secs.size(); i++){
			Stat newStat = secs.get(i).getOffStat(secsAmount.get(i));
			for(Stat stat : item.getStats().getStats())
				if(stat.getName().equals(newStat.getName())){
					newStat.setDuplicate(true);
					stat.addAmount(newStat.getAmount());
				}
			
			if(!newStat.isDuplicate())
				item.addStat(newStat);
		}
		return (Weapon) item;
	}

	@Override
	public String getName() {
		return "Knife";
	}
	
}
