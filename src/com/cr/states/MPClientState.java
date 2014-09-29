package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.game.EntityManager;
import com.cr.game.GameStateManager;
import com.cr.net.client.Client;
import com.cr.world.World;

public class MPClientState extends GameState{
	
	private static Client client;
	World w;

	public MPClientState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	
	@Override
	public void init() {
		w = new World();
		client = new Client("192.168.0.2");
		client.start();
		String userName = "anders";
		EntityManager.getHero().setUserName(userName);
		String message = "00" + userName;
		client.sendData(message.getBytes());
	}

	@Override
	public void tick(float dt) {
		w.tick(dt);
		
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
