package com.cr.item.weapon;

import java.awt.Rectangle;

import com.cr.combat.attack.OneHandAttack;
import com.cr.entity.Collideable;
import com.cr.input.Mouse;
import com.cr.item.Item;
import com.cr.item.activation.ItemObject;
import com.cr.stats.Stat;
import com.cr.util.Camera;

public abstract class Weapon extends Item{
	
	protected Rectangle hitBox;
	protected int CDTimer, CD;
	protected ItemObject attack;
	
	protected Stat damageBase, damageDice, cooldown;
	
	public Weapon(String imageString, int horXOffset, int vertXOffset,
			int xOffset, int yOffset, String name) {
		super(imageString, horXOffset, vertXOffset, xOffset, yOffset, name);
		hitBox = new Rectangle(x0, x1, width, height);
		
		damageBase = new Stat("Damage_Base", 0);
		damageDice = new Stat("Damage_Dice", 0);
		cooldown = new Stat("Cooldown", 0);
	}
	
	@Override
	public void tick(float dt){
		hitBox.setLocation(x0, x1);
		CDTimer--;
		
		tickPassives(dt);
		
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
	
	@Override
	public void activate() {
		CD = (int) cooldown.getTotal();
		
		if(CDTimer < 0){
			attack();
			CDTimer = CD;
		}
	}
	
	public abstract void playHitSound();

	public Stat getDamageBase() {
		return damageBase;
	}

	public Stat getDamageDice() {
		return damageDice;
	}

	public Stat getCooldown() {
		return cooldown;
	}
	
	
}
