package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.RightHandBob;

public class RightHand extends PlayerPart{
	public RightHand(){
		super("herorighthand", new RightHandBob(), 0, 10, 5, 10);
	}
}
