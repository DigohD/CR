package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.RightHandBob;
import com.cr.item.weapon.Hammer;

public class RightHand extends PlayerPart{
	public RightHand(){
		super("herorighthand", new RightHandBob(), 10, -10, 5, 10);
		setItem(new Hammer());
	}
}
