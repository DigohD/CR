package com.cr.item.weapon.attack;

import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.body.PlayerPart;
import com.cr.item.activation.ItemActive;
import com.cr.util.Vector2f;

public class OneHand extends ItemActive{

	private Vector2f velocity;
	private boolean horizontal;
	private int changeTimer, phase;
	
	public OneHand() {
		Direction dir = Hero.currentDir;
		switch(dir){
			case SOUTH:
				velocity = new Vector2f(-3f, 10f);
				break;
			case EAST:
				horizontal = true;
				velocity = new Vector2f(8f, -3f);
				break;
			case NORTH:
				velocity = new Vector2f(3f, -10f);
				break;
			case WEST:
				horizontal = true;
				velocity = new Vector2f(-8f, -3f);
				break;
		}
		offset = new Vector2f(0, 0);
		phase = 0;
		changeTimer = 0;
	}
	
	public OneHand(Vector2f velocity, Vector2f offset) {
		this.velocity = velocity;
		this.offset = offset;
	}

	@Override
	public void tick(float dt) {
		changeTimer++;
		if(changeTimer > 2){
			changeTimer = 0;
			if(horizontal)
				switch(phase){
					case 0:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 1:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 2:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 3:
						dead = true;
						offset = new Vector2f(0, 0);
						return;
				}
			else
				switch(phase){
					case 0:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 1:
						velocity = new Vector2f(velocity.x, -velocity.y);
						phase++;
						break;
					case 2:
						velocity = new Vector2f(-velocity.x, velocity.y);
						phase++;
						break;
					case 3:
						dead = true;
						offset = new Vector2f(0, 0);
						return;
				}
		}
		offset = offset.add(velocity);
		System.out.println(velocity);
	}
	
	
	
}
