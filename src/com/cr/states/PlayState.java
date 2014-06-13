package com.cr.states;

import java.awt.image.BufferedImage;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoaderOld;
import com.cr.world.World;

public class PlayState extends GameState{

	private World w;
	private BufferedImage img;
	public boolean bg = false;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		img = ImageLoaderOld.getImage("stBG");
	
	}
	
	@Override
	public void init() {
		w = new World();
	}
	

	@Override
	public void tick(float dt) {
	
		if(KeyInput.esc){
			bg = true;
			gsm.push(new PauseState(gsm));
		}else if(Input.getKey(Input.C)){
//			bg = true;
			gsm.push(new InventoryState(gsm));
		}else if(Input.getKey(Input.M)){
//			bg = true;
			gsm.push(new MaterialsState(gsm));
		}else if(KeyInput.q){
//			bg = true;
			gsm.push(new StatsState(gsm));
		}else if(Input.getKey(Input.KEY_F1)){
//			bg = true;
			gsm.push(new BiomeTestState(gsm));
		}else if(Input.getKey(Input.KEY_F)){
//			bg = true;
			gsm.push(new CraftingState(gsm));
		}
		w.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		w.render(screen);
		//if(bg) g.drawImage(img, 0, 0, Window.getWidth(), Window.getHeight(), null);
	}

}
