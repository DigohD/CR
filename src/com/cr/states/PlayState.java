package com.cr.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.Hero;
import com.cr.gameEngine.Game;
import com.cr.gameEngine.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.util.Camera;
import com.cr.world.World;

public class PlayState extends GameState{

	private World w;
	private BufferedImage img;
	public boolean bg = false;
	private Hero hero;
	private Camera camera;
	

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		img = ImageLoader.getImage("stBG");
		hero = new Hero();
		camera = new Camera(hero, 100, 100);
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
		hero.tick(dt);
		camera.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
//		w.render(g, (int)camera.getPos().x, (int)camera.getPos().y);
		w.render(g, 0, 0);
		hero.render(g);
		if(bg) g.drawImage(img, 7, 6, Game.WIDTH, Game.HEIGHT, null);
		
	}

}
