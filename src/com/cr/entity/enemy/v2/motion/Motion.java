package com.cr.entity.enemy.v2.motion;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Tickable;

public abstract class Motion implements Tickable{
	
	protected Vector2f motionPoint;
	
	public Vector2f getMotion(){
		return motionPoint;
	}
}
