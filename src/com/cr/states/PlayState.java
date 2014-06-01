package com.cr.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.Hero;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera2;
import com.cr.world.World;

public class PlayState extends GameState{

	private World w;
	private BufferedImage img;
	public boolean bg = false;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		img = ImageLoader.getImage("stBG");
	
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
		}else if(KeyInput.c){
//			bg = true;
			gsm.push(new InventoryState(gsm));
		}else if(KeyInput.v){
//			bg = true;
			gsm.push(new MaterialsState(gsm));
		}else if(KeyInput.q){
//			bg = true;
			gsm.push(new StatsState(gsm));
		}else if(KeyInput.shift){
//			bg = true;
			gsm.push(new BiomeTestState(gsm));
		}
		w.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		w.render(g);
		if(bg) g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
	}

}
