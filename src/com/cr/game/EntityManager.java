package com.cr.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cr.combat.Projectile;
import com.cr.combat.loot.Loot;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.enemy.attack.EnemyProjectile;
import com.cr.entity.hero.Hero;
import com.cr.net.HeroMP;
import com.cr.stats.StatsSheet;
import com.cr.util.Camera;
import com.cr.world.World;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class EntityManager {
	
	private static List<Tickable> tickableEntities;
	private static List<Tickable> teToAdd;
	private static List<Renderable> renderableEntities;
	private static List<Renderable> deToAdd;
	
	private static List<Entity> mainAdds;
	
	private static Hero hero;
	
	public EntityManager(World world){
		tickableEntities = new ArrayList<Tickable>();
		teToAdd = new ArrayList<Tickable>();
		renderableEntities = new ArrayList<Renderable>();
		deToAdd = new ArrayList<Renderable>();
		
		mainAdds = new ArrayList<Entity>();
		
		hero = new Hero(world);
//		HeroMP h = new HeroMP(hero.position.clone());
//		h.init();
	}
	
	public static void clear(){
		tickableEntities.clear();
		teToAdd.clear();
		renderableEntities.clear();
		deToAdd.clear();
		
		mainAdds.clear();
	}
	
	public static void addByMainThread(Entity e){
		mainAdds.add(e);
	}
	
	public static void addEntity(Entity e){
		if(e instanceof Tickable){
			Tickable t = (Tickable) e;
			teToAdd.add(t);
		}
		if(e instanceof Renderable){
			Renderable r = (Renderable) e;
			deToAdd.add(r);
		}
		if(e instanceof Enemy){
			Enemy c = (Enemy) e;
			CollisionManager.addEnemy(c);
		}
		if(e instanceof Loot){
			Loot c = (Loot) e;
			c.init();
			CollisionManager.addLoot(c);
		}
		if(e instanceof Projectile){
			Projectile c = (Projectile) e;
			CollisionManager.addProjectile(c);
		}
		if(e instanceof EnemyProjectile){
			EnemyProjectile c = (EnemyProjectile) e;
			CollisionManager.addEnemyProjectile(c);
		}
	}
	
	public static void removeEntity(Entity e){
		if(e instanceof Tickable){
			Tickable t = (Tickable) e;
			tickableEntities.remove(t);
		}
		if(e instanceof Renderable){
			Renderable r = (Renderable) e;
			renderableEntities.remove(r);
		}
		if(e instanceof Enemy){
			Enemy c = (Enemy) e;
			CollisionManager.removeEnemy(c);
		}
		if(e instanceof Loot){
			Loot c = (Loot) e;
			CollisionManager.removeLoot(c);
		}
		if(e instanceof Projectile){
			Projectile c = (Projectile) e;
			CollisionManager.removeProjectile(c);
		}
		if(e instanceof EnemyProjectile){
			EnemyProjectile c = (EnemyProjectile) e;
			CollisionManager.removeEnemyProjectile(c);
		}
	}
	
	private void removeDeadEntities(){
		for(int i = 0; i < tickableEntities.size(); i++){
			Tickable t = tickableEntities.get(i);
			Entity e = null;
			if(t instanceof Entity)
				e = (Entity) t;
			if(!e.isLive())
				removeEntity(e);
		}
	}
	
	public void tick(float dt){
		//copy everything from the toaddlists in order to avoid concurrent modification error 
		for(Tickable t : teToAdd)
			tickableEntities.add(t);
		for(Renderable d : deToAdd)
			renderableEntities.add(d);
		if(deToAdd.size() > 0)
			depthSort();		
		
		teToAdd.clear();
		deToAdd.clear();
		
		for(Entity mainAdd : mainAdds)
			addEntity(mainAdd);
		mainAdds.clear();
		
		removeDeadEntities();
		CollisionManager.collisionCheck(hero);
		
		for(Tickable t : tickableEntities)
			t.tick(dt);
		
		if(hero.isLive())
			hero.tick(dt);
	}
	
	int clipCount;
	
	public void render(Screen screen){
		boolean heroRendered = false;
		Rectangle ScreenRect = new Rectangle((int) Camera.getCamX(), (int) Camera.getCamY(), Window.getWidth(), Window.getHeight());
		for(Renderable r : renderableEntities){
			if(r.getRect() != null){
				if(ScreenRect.contains(r.getRect()) || ScreenRect.intersects(r.getRect()))
					r.render(screen);
			}
			if(!heroRendered && ((Entity) r).getPosition().y + r.getSprite().getSpriteHeight() > hero.getRect().y + hero.getRect().height)
				if(hero.isLive()){
					hero.render(screen);
					heroRendered = true;
				}
		}
		if(!heroRendered)
			hero.render(screen);
	}

	public static Hero getHero() {
		return hero;
	}

	private void depthSort(){
		try{
			java.util.Collections.sort(renderableEntities, new DepthComp());
		}catch(IllegalArgumentException e){
			
		}
	}
	
}
