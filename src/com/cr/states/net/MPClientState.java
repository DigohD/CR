package com.cr.states.net;

import java.util.List;

import com.cr.engine.graphics.Screen;
import com.cr.entity.hero.HeroMP;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.client.Client;
import com.cr.states.GameState;
import com.cr.world.World;

public class MPClientState extends GameState{
	
	private List<HeroMP> mockUps;
	private static Client client;
	private World w;
	
	public static boolean worldAssembled = false;

	public MPClientState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	
	@Override
	public void init() {
		
		client = new Client("192.168.0.2");
		client.start();
		mockUps = client.getHeroMockups();
		String userName = "anden";
		
		String message = "00" + userName;
		client.sendData(message.getBytes());
		
		while(!worldAssembled){
			//System.out.println("LOOP");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		//System.out.println(client.pixels.size());
		
		w = new World(client.pixels, client.width, client.height);
		
		EntityManager.getHero().setUserName(userName);
		
	}

	@Override
	public void tick(float dt) {
		w.tick(dt);
		for(int i = 0; i < mockUps.size(); i++){
			if(mockUps.get(i).getSprite() == null)
				mockUps.get(i).init();
		}
	}

	@Override
	public void render(Screen screen) {
		w.render(screen);
		
	}
	
	public static void close(){
		client.stop();
	}


	public static Client getClient() {
		return client;
	}

}
