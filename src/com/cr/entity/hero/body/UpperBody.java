package com.cr.entity.hero.body;

import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.anim.BodyBob;
import com.cr.item.Item;

public class UpperBody extends PlayerPart{

//	private BodyBob anim = new BodyBob();
	
	public UpperBody() {
		super("heroupperbody", new BodyBob(), 0, 0, 0, 0);
	}
	
	public UpperBody(Vector2f pos) {
		super("heroupperbody", new BodyBob(), 0, 0, 0, 0, pos);
	}

}
