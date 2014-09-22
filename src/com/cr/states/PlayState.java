package com.cr.states;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.input.Input;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.world.World;

public class PlayState extends GameState{

	private World w;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		w = new World();
	}
	
	@Override
	public void tick(float dt) {
		if(KeyInput.esc){
			gsm.push(new PauseState(gsm));
		}else if(Input.getKey(Input.C)){ 
			gsm.push(new InventoryState(gsm));
		}else if(Input.getKey(Input.M)){
			gsm.push(new CraftingState(gsm, new Forge()));
		}else if(KeyInput.q){
			gsm.push(new StatsState(gsm));
		}else if(Input.getKey(Input.F1)){
			gsm.push(new BiomeTestState(gsm));
		}else if(KeyInput.f){
			gsm.push(new CraftingState(gsm, new Forge()));
		}
		w.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		w.render(screen);
	}

}
