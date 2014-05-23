package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.LeftHandBob;
import com.cr.entity.hero.anim.RightHandBob;

public class LeftHand extends PlayerPart{
	public LeftHand(){
		super("herolefthand", new LeftHandBob(), 10, 10, 5, 10);
	}
}
