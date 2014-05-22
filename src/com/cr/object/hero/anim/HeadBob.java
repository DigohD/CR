package com.cr.object.hero.anim;

import com.cr.object.Tickable;
import com.cr.util.Vector2f;

public class HeadBob implements Tickable{

	private Vector2f offset = new Vector2f(0, 0);
	private Vector2f yVelocity = new Vector2f(0, 4f);
	private Vector2f xVelocity = new Vector2f(2f, 0);
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
		
		if(offset.y > 3)
			isGoingUp = true;
		else if(offset.y < -3)
			isGoingUp = false;
		
		if(offset.x > 3)
			isGoingRight = false;
		else if(offset.x < -3)
			isGoingRight = true;
		
		System.out.println(offset);
	}

	public Vector2f getOffset() {
		return offset;
	}
	
	

}
