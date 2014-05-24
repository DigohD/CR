package com.cr.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.item.weapon.MeleeWeapon;

public class CollisionManager {
	
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	
	public static void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	public static void removeEnemy(Enemy e){
		enemies.remove(e);
	}
	
	public static void clear(){
		enemies.clear();
	}
	
	private static boolean collisionBetween(Rectangle r1, Rectangle r2) {
		if (r1.intersects(r2) || r1.contains(r2))
			return true;
		return false;
	}
	
	public static void collisionCheck(Hero hero) {
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (hero != null && e != null && hero.isLive()){
				if (collisionBetween(hero.getRect(), e.getRect())){
					e.collisionWith(hero);
					hero.collisionWith(e);
				}
				if(Hero.getRightHand().getItem() != null && Hero.getRightHand().getItem() instanceof MeleeWeapon){
					MeleeWeapon weapon = (MeleeWeapon) Hero.getRightHand().getItem();
					if(collisionBetween(weapon.getRect(), e.getRect())){
						e.collisionWith(weapon);
						weapon.collisionWith(e);
					}
				}
				if(Hero.getLeftHand().getItem() != null && Hero.getLeftHand().getItem() instanceof MeleeWeapon){
					MeleeWeapon weapon = (MeleeWeapon) Hero.getLeftHand().getItem();
					if(collisionBetween(weapon.getRect(), e.getRect())){
						e.collisionWith(weapon);
						weapon.collisionWith(e);
					}
				}
//				if(collisionBetween(Hero.getLeftHand().getKnife().getRect(), e.getRect())){
//					e.collisionWith(Hero.getLeftHand().getKnife());
//					Hero.getLeftHand().getKnife().collisionWith(e);
//				}
			}
		}
		
	}

}
