package com.cr.crafting.material.loot;

public class LootEntry {

	protected int ID;
	protected float weight;
	protected float startValue;
	
	public LootEntry(int ID, float weight){
		this.weight = weight;
		this.ID = ID;
	}

	public float getWeight() {
		return weight;
	}

	public int getID() {
		return ID;
	}

	public float getStartValue() {
		return startValue;
	}

	public void setStartValue(float startValue) {
		this.startValue = startValue;
	}
}
