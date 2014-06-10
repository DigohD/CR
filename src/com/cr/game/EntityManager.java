package com.cr.game;

import java.util.ArrayList;
import java.util.List;

import com.cr.engine.graphics.Screen;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.Enemy;
import com.cr.entity.hero.Hero;
import com.cr.item.activation.Projectile;
import com.cr.world.World;

public class EntityManager {
	
	private static List<Tickable> tickableEntities;
	private static List<Tickable> teToAdd;
	private static List<Renderable> renderableEntities;
	private static List<Renderable> deToAdd;
	
	private static Hero hero;
	
	public EntityManager(World world){
		tickableEntities = new ArrayList<Tickable>();
		teToAdd = new ArrayList<Tickable>();
		renderableEntities = new ArrayList<Renderable>();
		deToAdd = new ArrayList<Renderable>();
		
		hero = new Hero(world);
	}
	
	public static void clear(){
		tickableEntities.clear();
		teToAdd.clear();
		renderableEntities.clear();
		deToAdd.clear();
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
		if(e instanceof Projectile){
			Projectile c = (Projectile) e;
			CollisionManager.addProjectile(c);
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
		if(e instanceof Projectile){
			Projectile c = (Projectile) e;
			CollisionManager.removeProjectile(c);
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
				
		teToAdd.clear();
		deToAdd.clear();
		
		removeDeadEntities();
		CollisionManager.collisionCheck(hero);
		
		for(Tickable t : tickableEntities)
			t.tick(dt);
		
		if(hero.isLive())
			hero.tick(dt);
	}
	
	public void render(Screen screen){
		for(Renderable r : renderableEntities)
			r.render(screen);
		if(hero.isLive())
			hero.render(screen);
	}

	public static Hero getHero() {
		return hero;
	}

}
