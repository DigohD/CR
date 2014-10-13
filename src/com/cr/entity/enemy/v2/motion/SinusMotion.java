package com.cr.entity.enemy.v2.motion;

import com.cr.engine.core.Vector2f;

public class SinusMotion extends Motion{

	public enum MotionAxis {X, Y}
	
	protected float amplitude, speed, offset, angle;
	protected MotionAxis axis;

	public SinusMotion(float amplitude, float speed, float offset,
			MotionAxis axis) {
		super();
		this.amplitude = amplitude;
		this.speed = speed;
		this.offset = offset;
		this.axis = axis;
	}

	@Override
	public void tick(float dt) {
		switch(axis){
			case X:
				motionPoint = new Vector2f((float) (Math.sin((angle + offset)) * amplitude), 0);
				break;
			case Y:
				motionPoint = new Vector2f(0, (float) (Math.sin((angle + offset)) * amplitude));
				break;
		}
		angle = angle + (dt * speed);
		if(angle >= 360)
			angle = angle - 360;
	}
	
}
