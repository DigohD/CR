package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.BodyBob;
import com.cr.item.Item;

public class Body extends PlayerPart{

//	private BodyBob anim = new BodyBob();
	
	public Body() {
		super("herobody", new BodyBob(), 0, 0, 0, 0);
	}

}
