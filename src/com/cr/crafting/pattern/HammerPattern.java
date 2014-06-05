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
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.Weapon;

public class HammerPattern extends Pattern{
	
	public HammerPattern(){
		super("hammerpattern");
		bases.add(Base.COPPER);
	}
	
	@Override
	public void startNew(){
		item = new Hammer();
		secs.clear();
		secsAmount.clear();
		baseMaterial = null;
		baseAmount = 0;
	}
	
	@Override
	public void applyMaterial(Material material, int amount){
		material.affectBase(baseMaterial, amount);
		
		for(Material sec : secs)
			sec.affectSec(material, amount);
		
		secs.add(material);
		secsAmount.add(amount);
	}
	
	@Override
	public void applyBaseMaterial(Material material, int amount){
		baseMaterial = material;
		if(material instanceof Copper){
			baseMaterial.getCurve().addPerMod(0.5f);
			baseMaterial.getCurve().addAmpMod(0.3f);
			baseMaterial.getCurve().addOffset(-0.15f);
			baseAmount = amount;
		}
	}
	
	@Override
	public Hammer generateItem(){
		Damage dmg = new Damage(baseMaterial.getCurve().getFunctionValue(baseAmount),
				baseMaterial.getCurve().getFunctionValue(baseAmount));
		CoolDown cd = new CoolDown(180 + (baseAmount / 4));
		
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
		return (Hammer) item;
	}
	
}
