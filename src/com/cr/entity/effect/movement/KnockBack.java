package com.cr.entity.effect.movement;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Mob;
import com.cr.entity.effect.TimedEffect;
import com.cr.util.Randomizer;

public class KnockBack extends TimedEffect{

	protected Vector2f pushDistance;
	protected Vector2f decay;
	
	public KnockBack(int lifetime, int applyInterval, Mob owner, Mob giver, Vector2f pushDistance) {
		super(lifetime, applyInterval, owner, giver);
		this.pushDistance = pushDistance;
		decay = pushDistance.div(lifetime);
	}

	@Override
	public void applyEffect() {
		owner.push(pushDistance);
		pushDistance = pushDistance.sub(decay);
	}

	@Override
	public void removeEffect() {
		// TODO Auto-generated method stub
	}

	
	
	
}
