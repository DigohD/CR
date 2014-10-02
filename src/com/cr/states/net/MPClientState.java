package com.cr.states.net;

import com.cr.engine.graphics.Screen;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.HeroMP;
import com.cr.net.client.Client;
import com.cr.states.GameState;
import com.cr.world.World;

public class MPClientState extends GameState{
	
	private static Client client;
	private World w;
	
	public static boolean worldAssembled = false;

	public MPClientState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		client = new Client("192.168.0.176", 1331);
		client.start();
		String userName = "anden";
		
		String message = "00" + userName;
		client.sendData(message.getBytes());
		
		while(!worldAssembled){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		w = new World(client.pixels, client.width, client.height);
		
		EntityManager.getHero().setUserName(userName);
	}

	@Override
	public void tick(float dt) {
		w.tick(dt);
		
		for(String name : client.getClientsMap().keySet()){
			HeroMP h = client.getClientsMap().get(name);
			if(h.getSprite() == null)
				h.init();
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
