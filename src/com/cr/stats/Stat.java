package com.cr.stats;

import java.util.HashMap;

public class Stat{

	protected String name;
	protected float base, total;
	protected HashMap<String, Float> addMods = new HashMap<String, Float>();
	protected HashMap<String, Float> mulMods = new HashMap<String, Float>();
	
	public Stat(String name, float base){
		this.name = name;
		this.base = base;
	}
	
	public void calculateTotal(){
		total = base;
		
		for(String x : addMods.keySet()){
			total = total + addMods.get(x);
		}
		
		float mulTotal = 1;
		for(String x : mulMods.keySet()){
			mulTotal = mulTotal + mulMods.get(x);
		}
		
		total = total * mulTotal;
	}
	
	public void addAddmod(String ID, float amount){
		addMods.put(ID, amount);
		calculateTotal();
	}
	
	public void addMulmod(String ID, float amount){
		mulMods.put(ID, amount);
		calculateTotal();
	}
	
	public void removeAddmod(String ID, float amount){
		addMods.remove(ID);
		calculateTotal();
	}
	
	public void removeMulmod(String ID, float amount){
		mulMods.remove(ID);
		calculateTotal();
	}
	
	public void setNewBase(float newBase){
		base = newBase;
	}

	public float getTotal() {
		return total;
	}
	
	
}
