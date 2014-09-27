package com.cr.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cr.combat.Projectile;
import com.cr.entity.Collideable;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.attack.EnemyProjectile;
import com.cr.entity.hero.Hero;

public class CollisionManager {
	
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	private static List<Collideable> misc = new ArrayList<Collideable>();
//	private static List<Loot> loot = new ArrayList<Loot>();
	private static List<Projectile> playerProjectiles = 
			new ArrayList<Projectile>();
	private static List<EnemyProjectile> enemyProjectiles = 
			new ArrayList<EnemyProjectile>();
	
	public static void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	public static void removeEnemy(Enemy e){
		enemies.remove(e);
	}
	
//	public static void addLoot(Loot e){
//		loot.add(e);
//	}
//	
//	public static void removeLoot(Loot e){
//		loot.remove(e);
//	}
	
	public static void addProjectile(Projectile e){
		playerProjectiles.add(e);
	}
	
	public static void removeProjectile(Projectile e){
		playerProjectiles.remove(e);
	}
	
	public static void addHitable(Collideable c){
		misc.add(c);
	}
	
	public static void removeHitable(Collideable c){
		misc.remove(c);
	}
	
	public static void addEnemyProjectile(EnemyProjectile c) {
		enemyProjectiles.add(c);
	}
	
	public static void removeEnemyProjectile(EnemyProjectile c) {
		enemyProjectiles.remove(c);
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
		
		for(int i = 0; i < enemyProjectiles.size(); i++){
			EnemyProjectile p = enemyProjectiles.get(i);
			if (collisionBetween(p.getRect(), hero.getRect())){
				hero.collisionWith(p);
				p.collisionWith(hero);
			}
		}
		
		for(int i = 0; i < misc.size(); i++){
			Collideable c = misc.get(i);
			for(int j = 0; j < playerProjectiles.size(); j++){
				Projectile p = playerProjectiles.get(j);
				if (collisionBetween(p.getRect(), c.getRect())){
					c.collisionWith(p);
					p.collisionWith(c);
				}
			}
		}
		
//		for(int i = 0; i < loot.size(); i++){
//			Loot p = loot.get(i);
//			if (collisionBetween(p.getRect(), hero.getRect())){
//				hero.collisionWith(p);
//				p.collisionWith(hero);
//			}
//		}
		
	}

	

}
