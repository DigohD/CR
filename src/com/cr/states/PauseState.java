package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;

public class PauseState extends GameState{

	public PauseState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public void tick(float dt) {
		if(KeyInput.enter) gsm.removeTop();
		if(KeyInput.c) {
			gsm.removeTop();
			gsm.removeTop();
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawString("PRESS ENTER TO PLAY", Game.WIDTH/2-100, Game.HEIGHT/2);
		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
		g.drawString("PRESS ESC TO EXIT GAME", Game.WIDTH/2-100, Game.HEIGHT/2+60);
	}

}
