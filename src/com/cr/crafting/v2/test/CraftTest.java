package com.cr.crafting.v2.test;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Copper;
import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;
import com.cr.crafting.v2.pattern.*;
import com.cr.crafting.v2.pattern.weapon.KnifePattern;
import com.cr.crafting.v2.station.Forge;
import com.cr.item.Item;
import com.cr.item.weapon.CopperKnife;
import com.cr.util.Randomizer;

public class CraftTest {

	public void craftTest(){
		
		ArrayList<Integer> Ba = new ArrayList<Integer>();
		ArrayList<Integer> Bl = new ArrayList<Integer>();
		ArrayList<Integer> Ha = new ArrayList<Integer>();
		ArrayList<Integer> Te = new ArrayList<Integer>();
		ArrayList<Integer> Fl = new ArrayList<Integer>();
		
		for(int i = 0; i < 1000; i++){
			Forge f = new Forge();
			f.setPattern(new KnifePattern());
			
			int randomHeat = Randomizer.getInt(500, 1300);
			int randomTime = Randomizer.getInt(1, 400);
			int randomAmount = Randomizer.getInt(1, 100);
			Copper c = new Copper();
			
			f.setHeat(randomHeat);
			f.setTime(randomTime);
			f.addMaterial(c, randomAmount);
			f.process();
			
			State s = f.getBase().getState();
			Item item = f.craft();
			
			CopperKnife ck = (CopperKnife) item;
			float dps = (ck.getDamageBase().getTotal() + ck.getDamageDice().getTotal()) / 2;
			dps = dps / (ck.getCooldown().getTotal() / 60);
			
			if(s == State.BALANCED)
				Ba.add((int) dps);
			if(s == State.HARDENED)
				Ha.add((int) dps);
			if(s == State.TEMPERED)
				Te.add((int) dps);
			if(s == State.BLASTED)
				Bl.add((int) dps);
			if(s == State.FLASHED)
				Fl.add((int) dps);
		}
		
		int total = 0;
		for(Integer x : Ba)
			total = total + x;
		total = total / Ba.size();
		
		System.out.println("Balanced: " + total);
		
		total = 0;
		for(Integer x : Ha)
			total = total + x;
		total = total / Ha.size();
		
		System.out.println("Hardened: " + total);
		
		total = 0;
		for(Integer x : Te)
			total = total + x;
		total = total / Te.size();
		
		System.out.println("Tempered: " + total);
		
		total = 0;
		for(Integer x : Bl)
			total = total + x;
		total = total / Bl.size();
		
		System.out.println("Blasted: " + total);
		
		total = 0;
		for(Integer x : Fl)
			total = total + x;
		total = total / Fl.size();
		
		System.out.println("Flashed: " + total);
	}
	
}
