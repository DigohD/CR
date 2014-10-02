package com.cr.states.net;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.input.Input;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.net.HeroMP;
import com.cr.net.client.Client;
import com.cr.net.packets.DisconnectPacket06;
import com.cr.states.GameState;
import com.cr.states.StatsState;
import com.cr.states.crafting.CraftInitState;
import com.cr.states.inventory.InventoryState;
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
		client = new Client("localhost", 1331);
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
			//DisconnectPacket06 packet = new DisconnectPacket06(EntityManager.getHero().getUserName());
			client.sendData("06:anden".getBytes());
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
