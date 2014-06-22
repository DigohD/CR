package com.cr.combat;

import java.util.ArrayList;

public class DamagePacket{

	private ArrayList<Damage> dmgs = new ArrayList<Damage>();

	public void addDamage(Damage dmg){
		for(Damage x : dmgs)
			if(x.getType() == dmg.getType()){
				x.addDamageBase(dmg.getDamageBase());
				x.addDamageDice(dmg.getDamageDice());
				return;
			}
		dmgs.add(dmg);
	}
	
	public ArrayList<Damage> getDmgs() {
		return dmgs;
	}
	
	
	
}
