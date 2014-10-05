package com.cr.entity.hero.body;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.anim.HeadBob;

public class Head extends PlayerPart{

//	private HeadBob anim = new HeadBob();
	
	public Head() {
		super("herohead", new HeadBob(), 2, 0, 5, -14);
	}
	
	public Head(Vector2f pos, Transform t) {
		super("herohead", new HeadBob(), 2, 0, 5, -14, pos, t);
	}
}
