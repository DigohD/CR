package com.cr.combat;

import com.cr.util.Randomizer;

public class Damage{

	public enum DamageType {PHYSICAL}
	
	private float damageBase, damageDice;
	private DamageType type;
	
	public Damage(float damageBase, float damageDice, DamageType type){
		this.type = type;
		this.damageBase = damageBase;
		this.damageDice = damageDice;
	}
	
	public float calculateDamage(){
		return damageBase + Randomizer.getFloat(0, damageDice);
	}
	
	public float getDamageBase() {
		return damageBase;
	}

	public void setDamageBase(float damageBase) {
		this.damageBase = damageBase;
	}
	
	public void addDamageBase(float damageBase) {
		this.damageBase += damageBase;
		if(damageBase < 1)
			damageBase = 1;
	}

	public float getDamageDice() {
		return damageDice;
	}

	public void setDamageDice(float damageDice) {
		this.damageDice = damageDice;
		if(damageBase < 0)
			damageDice = 0;
	}
	
	public void addDamageDice(float damageDice) {
		this.damageDice += damageDice;
	}

	public DamageType getType() {
		return type;
	}

	public void setType(DamageType type) {
		this.type = type;
	}
	
	
	
	
}
