package com.cr.states.net;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.net.HeroMP;
import com.cr.net.HeroMPServer;
import com.cr.net.server.Server;
import com.cr.states.GameState;
import com.cr.states.StatsState;
import com.cr.states.crafting.CraftInitState;
import com.cr.states.inventory.InventoryState;
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
		hero.setUserName("dgd");
		server = new Server(12121, world);
		server.start();
	}

	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.C))
			gsm.push(new InventoryState(gsm));
		if(Input.getKey(Input.M))
			gsm.push(new CraftInitState(gsm, new Forge()));
		if(Input.getKey(Input.Q))
			gsm.push(new StatsState(gsm));
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
		
		world.tick(dt);
		
		for(String name : server.getClientsMap().keySet()){
			HeroMPServer h = server.getClientsMap().get(name);
			if(h.getSprite() == null)
				h.init();
		}
		
	
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
