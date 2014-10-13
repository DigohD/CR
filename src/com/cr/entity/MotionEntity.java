package com.cr.entity;

import com.cr.engine.core.Vector2f;
import com.cr.entity.enemy.v2.motion.Motion;

public abstract class MotionEntity {

	public abstract Vector2f applyMotion(Vector2f position);
	public abstract void addMotion(Motion motion);
	public abstract void removeMotion(Motion motion);

}
