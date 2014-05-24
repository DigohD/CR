package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.LeftHandBob;
import com.cr.item.weapon.Knife;

public class LeftHand extends PlayerPart{
	
	private Knife knife;
	public LeftHand(){
		super("herolefthand", new LeftHandBob(), 10, 10, 5, 10);
		knife = new Knife();
		setItem(knife);
	}
	
	public Knife getKnife() {
		return knife;
	}

	@Override
	public void tick(float dt) {
		knife.tick(dt);
		bob.tick(dt);
		if(item != null && item.getItemActive() != null)
			if(!item.getItemActive().isDead())
				item.getItemActive().tick(dt);
	}
}
