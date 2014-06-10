package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.entity.Collideable;
import com.cr.entity.hero.StatsSheet;
import com.cr.input.Mouse;
import com.cr.item.Item;
import com.cr.item.activation.ItemObject;
import com.cr.item.weapon.attack.OneHandAttack;
import com.cr.util.Camera;

public abstract class Weapon extends Item{
	
	protected Rectangle hitBox;
	protected int CDTimer, CD;
	protected ItemObject attack;
	
	public Weapon(String imageString, int horXOffset, int vertXOffset,
			int xOffset, int yOffset, String name) {
		super(imageString, horXOffset, vertXOffset, xOffset, yOffset, name);
		hitBox = new Rectangle(x0 + (int)Camera.getCamX(), y0 + (int)Camera.getCamY(), width, height);
	}
	
	@Override
	public void tick(float dt){
		hitBox.setLocation(x0 + (int)Camera.getCamX(), y0 + (int)Camera.getCamY());
		CDTimer--;
		
		tempXOffset = 0;
		tempYOffset = 0;
		if(attack != null && !attack.isDead()){
			attack.tick(dt);
			tempXOffset = (int) attack.getOffset().x;
			tempYOffset = (int) attack.getOffset().y;
		}
	}

	public Rectangle getRect(){
		return hitBox;
	}
	
	public abstract void attack();

}
