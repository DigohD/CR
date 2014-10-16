package com.cr.util.enemy;

import com.cr.engine.core.Vector2f;
import com.cr.entity.Mob;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.net.HeroMPServer;
import com.cr.net.NetStatus;
import com.cr.states.net.MPHostState;

public class EnemyLogic {
	public static Mob getNearestHero(Vector2f enemyPosition){
		Mob nearestHero = EntityManager.getHero();
		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			for(String userName : MPHostState.getServer().getClientsMap().keySet()){
				HeroMPServer player = MPHostState.getServer().getClientsMap().get(userName);
				if(enemyPosition.sub(player.getPosition()).length() < enemyPosition.sub(nearestHero.getPosition()).length()){
					nearestHero = player;
				}
			}
		}
		return nearestHero;
	}
	
	public static float getDistanceToHero(Vector2f enemyPosition, Mob hero){
		return enemyPosition.sub(hero.getPosition()).length();
	}
	
	public static Vector2f getDirectionToHero(Vector2f enemyPosition, Mob hero){
		return hero.getPosition().sub(enemyPosition).normalize();
	}
}
