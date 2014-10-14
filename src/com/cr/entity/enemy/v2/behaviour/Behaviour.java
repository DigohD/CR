package com.cr.entity.enemy.v2.behaviour;

import com.cr.util.ai.ArcheType;
import com.cr.util.ai.Race;

public abstract class Behaviour{
	
	public abstract void tick(float dt);
	
	public abstract ArcheType[] getCompatibleArcheTypes();
	public abstract Race[] getCompatibleRaces();
	
}
