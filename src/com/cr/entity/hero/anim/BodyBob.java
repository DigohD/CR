package com.cr.entity.hero.anim;

import com.cr.entity.Tickable;
import com.cr.util.Vector2f;

public class BodyBob implements Tickable{

	private Vector2f offset = new Vector2f(0, 0);
	private Vector2f yVelocity = new Vector2f(0, 2f);
	private Vector2f xVelocity = new Vector2f(1f, 0);
	private boolean isGoingUp = true, isGoingRight = true;
	
	@Override
	public void tick(float dt) {
		if(isGoingUp)
			offset = offset.sub(yVelocity.mul(dt));
		else
			offset = offset.add(yVelocity.mul(dt));
		
		if(isGoingRight)
			offset = offset.add(xVelocity.mul(dt));
		else
			offset = offset.sub(xVelocity.mul(dt));
		
		if(offset.y > 1.5f)
			isGoingUp = true;
		else if(offset.y < -1.5f)
			isGoingUp = false;
		
		if(offset.x > 1.5f)
			isGoingRight = false;
		else if(offset.x < -1.5f)
			isGoingRight = true;
		
		System.out.println(offset);
	}

	public Vector2f getOffset() {
		return offset;
	}
	
	

}
