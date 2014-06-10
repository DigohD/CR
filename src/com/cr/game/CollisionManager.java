package com.cr.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cr.entity.emitter.ImpactEmitter;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.item.activation.Projectile;
import com.cr.item.weapon.Weapon;
import com.cr.engine.core.Vector2f;

public class CollisionManager {
	
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	private static List<Projectile> playerProjectiles = 
			new ArrayList<Projectile>();
	
	public static void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	public static void removeEnemy(Enemy e){
		enemies.remove(e);
	}
	
	public static void addProjectile(Projectile e){
		playerProjectiles.add(e);
	}
	
	public static void removeProjectile(Projectile e){
		playerProjectiles.remove(e);
	}
	
	public static void clear(){
		enemies.clear();
		playerProjectiles.clear();
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
			}
			for(int j = 0; j < playerProjectiles.size(); j++){
				Projectile p = playerProjectiles.get(j);
				if (collisionBetween(p.getRect(), e.getRect())){
					e.collisionWith(p);
					p.collisionWith(e);
				}
			}
		}
		
	}

}
