package com.cr.entity.enemy.v2.motion;

import java.util.ArrayList;

import com.cr.engine.core.Vector2f;
import com.cr.entity.MotionEntity;

public class AniMotion extends MotionEntity{

	private ArrayList<Motion> motions = new ArrayList<Motion>();
	
	@Override
	public Vector2f applyMotion(Vector2f position) {
		Vector2f totalMotion = new Vector2f(0, 0);
		for(Motion x : motions){
			totalMotion = totalMotion.add(x.getMotion());
		}
		return position.add(totalMotion);
	}

	@Override
	public void addMotion(Motion motion) {
		motions.add(motion);
	}

	@Override
	public void removeMotion(Motion motion) {
		motions.remove(motion);
	}
	
	public void tick(float dt){
		for(Motion x : motions)
			x.tick(dt);
	}
	
}
