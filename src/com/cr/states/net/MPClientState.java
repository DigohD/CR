package com.cr.states.net;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.input.Input;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.net.HeroMP;
import com.cr.net.client.Client;
import com.cr.net.packets.Packet16Disconnect;
import com.cr.net.packets.Packet10Login;
import com.cr.states.GameState;
import com.cr.states.StatsState;
import com.cr.states.crafting.CraftInitState;
import com.cr.states.inventory.InventoryState;
import com.cr.world.World;

public class MPClientState extends GameState{
	
	private static Client client;
	private World w;
	
	public static boolean worldAssembled = false;
	
	public static String userName;

	public MPClientState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		client = new Client("192.168.0.2", 12121);
		client.start();
		userName = "anders";
		
		Packet10Login packet = new Packet10Login(userName);
		client.sendData(packet.getData());
		
		while(!worldAssembled){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		w = new World(client.pixels, client.getWidth(), client.getHeight());
		
		EntityManager.getHero().setUserName(userName);
	}

	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.C))
			gsm.push(new InventoryState(gsm));
		if(Input.getKey(Input.M))
			gsm.push(new CraftInitState(gsm, new Forge()));
		if(Input.getKey(Input.Q))
			gsm.push(new StatsState(gsm));
		if(Input.getKey(Input.ESCAPE)){
			System.out.println(EntityManager.getHero().getUserName());
			Packet16Disconnect packet = new Packet16Disconnect(EntityManager.getHero().getUserName());
			//String message = "16:" + EntityManager.getHero().getUserName();
			client.sendData(packet.getData());
			Game.stop();
		}
			
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
