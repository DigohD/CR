package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.RightHandBob;
import com.cr.item.weapon.Hammer;

public class RightHand extends PlayerPart{
	
	private Hammer hammer;
	
	public Hammer getHammer() {
		return hammer;
	}

	public RightHand(){
		super("herorighthand", new RightHandBob(), 10, -10, 5, 10);
		hammer = new Hammer();
		setItem(hammer);
	}
	
	@Override
	public void tick(float dt) {
		hammer.tick(dt);
		bob.tick(dt);
		if(item != null && item.getItemActive() != null)
			if(!item.getItemActive().isDead())
				item.getItemActive().tick(dt);
	}
	
	
}
