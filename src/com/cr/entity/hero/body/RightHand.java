package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.RightHandBob;
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.Weapon;

public class RightHand extends PlayerPart{
	
	public RightHand(){
		super("herorighthand", new RightHandBob(), 10, -10, 5, 10);
	}
	
	@Override
	public void tick(float dt) {
		if(item != null && item instanceof Weapon){
			Weapon weapon = (Weapon) item;
			weapon.tick(dt);
		}
		
		bob.tick(dt);
	}
	
	
}
