package com.cr.crafting.v2.material;

import java.awt.Image;
import java.util.ArrayList;

import com.cr.crafting.v2.property.Fragile;
import com.cr.crafting.v2.property.Property;
import com.cr.crafting.v2.property.Solid;
import com.cr.engine.graphics.Sprite;
import com.cr.item.statsMods.Stat;
import com.cr.item.statsMods.basic.CoolDown;
import com.cr.item.statsMods.basic.Damage;
import com.cr.util.ImageLoader;

public class Copper extends Material{

	private float mod1, mod2, mod3, mod4;
	
	public Copper(){
		properties = new ArrayList<Property>();		
		lowerHeatLimit = 500;
		higherHeatLimit = 1100;
		lowerTimeLimit = 30;
		higherTimeLimit = 300;
		balancedValue = 50;
		
		mod1 = 1f;
		mod2 = 1f;
		mod3 = 1f;
		mod4 = 1f;
		
		isPrimary = true;
		
		calculateMids();
		
		amount = 100;
		
		breakable = true;
		
		properties.add(new Solid());
	}

	@Override
	public String getName() {
		return "Copper";
	}

	@Override
	public Sprite getMaterialImage() {
		return new Sprite("copper");
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	protected void newMods() {
		mod1 = 1f + usedAmount / 75.0f;
		mod2 = 1f + usedAmount / 65.0f;
		mod3 = 1f + usedAmount / 60.0f;
		mod4 = 1f + usedAmount / 55.0f;
		
		System.out.println(mod1 + " . " + mod2 + " . " + mod3 + " . " + mod4);
	}

	@Override
	public ArrayList<Stat> getWeaponStats(ArrayList<Stat> stats) {
		int span = 1;
		
		Damage dmg;
		CoolDown CD;
		if(state == State.BALANCED){
			span = (int) (span * mod3 * mod2 * 2);
			
			dmg = new Damage(mod1, mod1 + span);
			CD = new CoolDown(10 * mod3 * mod4);
			
			dmg.modAmount(1.25f);
			CD.modAmount(0.75f);
			
			stats.add(dmg);
			stats.add(CD);
		}else if(state == State.BLASTED){
			span = (int) (span * mod1 * mod2 * mod3 * 1);
			
			dmg = new Damage(mod2 * mod3, (mod2 * mod3) + span);
			CD = new CoolDown(5 * mod1 * mod3 * mod4);
			
			dmg.modAmount(1.5f);
			CD.modAmount(1f);
			
			stats.add(dmg);
			stats.add(CD);
		}else if(state == State.FLASHED){
			span = (int) (span * mod1 * mod4 * mod3 * 1);

			dmg = new Damage(mod1 * mod3, (mod1 * mod3) + span);
			CD = new CoolDown(5 * mod2 * mod3 * mod4);
			
			dmg.modAmount(1f);
			CD.modAmount(0.5f);
			
			stats.add(dmg);
			stats.add(CD);
		}else if(state == State.HARDENED){
			span = (int) (span * mod1 * 2);

			dmg = new Damage(mod3, mod3 + span);
			CD = new CoolDown(10 * mod2 * mod4);
			
			dmg.modAmount(1.35f);
			CD.modAmount(0.85f);
			
			stats.add(dmg);
			stats.add(CD);
		}else if(state == State.TEMPERED){
			span = (int) (span * mod4 * 2);

			dmg = new Damage(mod1, mod1 + span);
			CD = new CoolDown(10 * mod3 * mod2);
			
			dmg.modAmount(1.15f);
			CD.modAmount(0.65f);
			
			stats.add(dmg);
			stats.add(CD);
		}
		return stats;
	}

	@Override
	public ArrayList<Stat> getArmorStats(ArrayList<Stat> stats) {
		return null;
	}
	
}
