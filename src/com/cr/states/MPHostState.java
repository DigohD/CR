package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.packets.MovePacket02;
import com.cr.net.server.Server;
import com.cr.world.World;

public class MPHostState extends GameState{
	
	private Server server;
	private World world;
	private Hero hero;
	
	public MPHostState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		server = new Server();
		server.start();
		world = new World();
		hero = EntityManager.getHero();
		hero.setUserName("Ders");
	}

	@Override
	public void tick(float dt) {
		world.tick(dt);
		MovePacket02 mp = new MovePacket02(hero.getUserName(), hero.getPos());
		server.sendDataToAllClients(mp.getData());
	}

	@Override
	public void render(Screen screen) {
		world.render(screen);
	}

}
