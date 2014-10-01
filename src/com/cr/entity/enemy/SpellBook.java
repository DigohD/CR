package com.cr.entity.enemy;

import java.util.ArrayList;

import com.cr.entity.Tickable;
import com.cr.entity.ability.Ability;

public class SpellBook implements Tickable{

	protected ArrayList<Ability> spells = new ArrayList<Ability>();

	@Override
	public void tick(float dt) {
		for(Ability x : spells){
			x.tick(dt);
			if(x.isAIActivate())
				x.activate();
		}
	}
	
	public void addSpell(Ability spell){
		spells.add(spell);
	}
	
	public void removeSpell(Ability spell){
		spells.remove(spell);
	}
}
