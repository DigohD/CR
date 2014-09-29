package com.cr.states;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.HeroMP;
import com.cr.entity.hero.HeroMPView;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.client.ClientInfo;
import com.cr.net.packets.MovePacket02;
import com.cr.net.server.Server;
import com.cr.world.World;

public class MPHostState extends GameState{
	
	private static Server server;
	private World world;
	private Hero hero;
	
	private HashMap<String, HeroMP> mockupHeroMap;
	private List<ClientInfo> clients;
	private List<HeroMP> mockUps;
	List<HeroMPView> mpViews;
	
	public MPHostState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		mpViews = new ArrayList<HeroMPView>();
		mockupHeroMap = new HashMap<String, HeroMP>();
		server = new Server();
		clients = server.getClients();
		mockUps = server.getMockups();
		server.start();
		world = new World();
		hero = EntityManager.getHero();
		hero.setUserName("Ders");
		
		//mockupHeroMap.put(hero.getUserName(), new HeroMP(hero.getUserName(), hero.getPos()));
	}

	@Override
	public void tick(float dt) {
		world.tick(dt);
		
		for(int i = 0; i < mockUps.size(); i++){
			if(mpViews.get(i) == null) {
				mpViews.add(new HeroMPView(mockUps.get(i)));
			}
		}
		
		MovePacket02 mp = new MovePacket02(hero.getUserName(), hero.getPos());
		server.sendDataToAllClients(mp.getData());
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

}
