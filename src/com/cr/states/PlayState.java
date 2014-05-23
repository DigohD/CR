package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.object.hero.Hero;
import com.cr.world.World;

public class PlayState extends GameState{
	
	private Hero hero;
	private World w;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		w = new World();
		hero = new Hero();
	}

	@Override
	public void tick(float dt) {
		if(KeyInput.space){
			gsm.pushState(new PauseState(gsm));
		}
		
		if(KeyInput.c){
			gsm.removeTop();
		}
		hero.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		w.render(g);
		hero.render(g);
		g.setColor(Color.RED);
		g.drawString("PRESS C TO RETURN TO MENU", 50, Game.HEIGHT/2);
		g.drawString("PRESS SPACE TO PAUSE", 50, Game.HEIGHT/2+30);
		g.drawString("PRESS ESC TO EXIT GAME", 50, Game.HEIGHT/2+60);
	}

}
