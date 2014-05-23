package com.cr.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.gameEngine.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
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
		}
		w.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		w.render(g);
		if(bg) g.drawImage(img, 7, 6, 460+12, 300+12, null);
	}

}
