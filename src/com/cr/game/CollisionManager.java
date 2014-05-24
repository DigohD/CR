package com.cr.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;

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
			if (hero != null && e != null && hero.isLive())
				if (collisionBetween(hero.getRect(), e.getRect())){
					e.collisionWith(hero);
					hero.collisionWith(e);
				}
		}
		
	}

}
