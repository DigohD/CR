package com.cr.entity.emitter;

import com.cr.entity.Entity;
import com.cr.entity.Tickable;
import com.cr.util.Vector2f;

public abstract class Emitter extends Entity implements Tickable{

	protected int lifeTime, timeLived;
	
	public Emitter(Vector2f position, int lifeTime) {
		super(position);
		this.lifeTime = lifeTime;
		this.timeLived = 0;
	}
	
	@Override
	public void tick(float dt) {
		timeLived++;
		if(timeLived > lifeTime)
			this.closeEmitter();
		emit();
	}
	
	public abstract void emit();

	public void closeEmitter(){
		live = false;
	}
}
