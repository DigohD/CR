package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.game.GameStateManager;
import com.cr.net.client.Client;

public class MPClientState extends GameState{
	
	private static Client client;

	public MPClientState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	
	@Override
	public void init() {
		client = new Client("localhost");
		client.start();
		String userName = "anders";
		String message = "00" + userName;
		client.sendData(message.getBytes());
	}

	@Override
	public void tick(float dt) {
		
		
	}

	@Override
	public void render(Screen screen) {
		
		
	}
	
	public static void close(){
		client.stop();
	}


	public static Client getClient() {
		return client;
	}

}
