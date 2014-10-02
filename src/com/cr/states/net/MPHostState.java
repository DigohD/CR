package com.cr.states.net;

import java.util.List;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.HeroMP;
import com.cr.net.server.Server;
import com.cr.states.GameState;
import com.cr.world.World;

public class MPHostState extends GameState{
	
	private static Server server;
	private static World world;
	private Hero hero;
	
	public MPHostState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		world = new World();
		hero = EntityManager.getHero();
		hero.setUserName("Ders");
		server = new Server(1331);
		server.start();
	}

	@Override
	public void tick(float dt) {
		world.tick(dt);
		
		for(String name : server.getClientsMap().keySet()){
			HeroMP h = server.getClientsMap().get(name);
			if(h.getSprite() == null)
				h.init();
		}
		
//		MovePacket02 mp = new MovePacket02(hero.getUserName(), hero.getPos());
//		server.sendDataToAllClients(mp.getData());
	}

	@Override
	public void render(Screen screen) {
		world.render(screen);
	}
	
	public static void close(){
		server.stop();
	}

	public static Server getServer() {
		return server;
	}

	public static World getWorld() {
		return world;
	}

}
