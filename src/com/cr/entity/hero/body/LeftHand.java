package com.cr.entity.hero.body;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.anim.LeftHandBob;
import com.cr.item.weapon.Weapon;

public class LeftHand extends PlayerPart{
	
	public LeftHand(){
		super("herolefthand", new LeftHandBob(), 10, 10, 5, 10);
	}
	
	public LeftHand(Vector2f pos, Transform t){
		super("herolefthand", new LeftHandBob(), 10, 10, 5, 10, pos, t);
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
