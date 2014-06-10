package com.cr.entity.hero.body;

import com.cr.entity.hero.anim.HeadBob;

public class Head extends PlayerPart{

//	private HeadBob anim = new HeadBob();
	
	public Head() {
		super("herohead", new HeadBob(), 2, 0, 5, -14);
	}
}
