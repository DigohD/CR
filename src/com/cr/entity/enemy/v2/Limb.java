package com.cr.entity.enemy.v2;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.v2.motion.AniMotionSet;
import com.cr.entity.enemy.v2.motion.AniMotionSet.MotionStatus;

public class Limb extends Entity{

	protected Enemy owner;
	protected Vector2f bodyOffset, renderPosition;

	protected AniMotionSet motionSet = new AniMotionSet();
	
	protected Sprite limbSprite;
	protected Sprite item;
	
	public Limb(Vector2f position, Enemy owner, Vector2f bodyOffset, Sprite limbSprite) {
		super(position);
		this.owner = owner;
		this.bodyOffset = bodyOffset;
		this.limbSprite = limbSprite;
		
		item = null;
	}

	public void tick(float dt) {
		motionSet.getActiveMotion().tick(dt);
		if(owner.isMoving){
			motionSet.setActiveMotion(MotionStatus.MOVING);
		}else
			motionSet.setActiveMotion(MotionStatus.IDLE);
		position = owner.getRenderPosition().add(bodyOffset).add(owner.getCenterOffset());
	}
	
	public void renderLimb(Screen screen){
		renderPosition = position.clone();
		renderPosition = motionSet.getActiveMotion().applyMotion(renderPosition);
		
		screen.renderSprite(limbSprite, renderPosition.x, renderPosition.y);
		if(item != null)
			screen.renderSprite(item, renderPosition.x, renderPosition.y + 1);
	}

	public AniMotionSet getMotionSet() {
		return motionSet;
	}

	public void setItem(Sprite item) {
		this.item = item;
	}
	
	
}
