package com.cr.entity.ability.self;

import com.cr.entity.Mob;
import com.cr.entity.ability.Ability;

public abstract class SelfAbility extends Ability{

	public SelfAbility(String name, Mob source, float cooldown) {
		super(name, source, cooldown);
	}
	
	@Override
	protected Mob findTarget(){
		return source;
	}

}
