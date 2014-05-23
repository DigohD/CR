package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.gameEngine.Display;
import com.cr.gameEngine.EntityManager;
import com.cr.gameEngine.Game;
import com.cr.gameEngine.GameStateManager;
import com.cr.input.KeyInput;

public class MenuState extends GameState{

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void tick(float dt) {
		if(KeyInput.enter){
			gsm.push(new PlayState(gsm));
		}
		if(KeyInput.esc){
			Display.restoreScreen();
			System.exit(0);
		}	
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.RED);
		g.drawString("PRESS ENTER TO PLAY", Game.WIDTH/2-100, Game.HEIGHT/2);
		g.drawString("PRESS ESC TO EXIT GAME", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

	

}
