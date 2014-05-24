package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.LeftHandBob;
import com.cr.item.weapon.Knife;
import com.cr.item.weapon.MeleeWeapon;

public class LeftHand extends PlayerPart{
	
	public LeftHand(){
		super("herolefthand", new LeftHandBob(), 10, 10, 5, 10);
		item = new Knife();
	}

	@Override
	public void tick(float dt) {
		if(item != null && item instanceof MeleeWeapon){
			MeleeWeapon weapon = (MeleeWeapon) item;
			weapon.tick(dt);
		}
			
		bob.tick(dt);
		if(item != null && item.getItemActive() != null)
			if(!item.getItemActive().isDead())
				item.getItemActive().tick(dt);
	}
}
