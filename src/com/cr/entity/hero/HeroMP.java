package com.cr.entity.hero;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Entity;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;

public class HeroMP extends Entity implements Tickable{
	
	private String userName;
	private Vector2f position;

	public HeroMP(String userName, Vector2f position){
		super(position);
		this.userName = userName;
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
	
	}

	public String getUserName() {
		return userName;
	}


	

}
