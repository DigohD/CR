package com.cr.game;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.states.MenuState;

public class Game extends Core{
	
	private GameStateManager gsm;
	
	public Game(){
		new ImageLoader();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		
		Pyrite c = new Pyrite();
		c.getCurve().testCurve();
	}

	@Override
	public void getInput() {
		KeyInput.tick();	
	}

	@Override
	public void tick(float dt) {
		gsm.tick(dt);
	}

	@Override
	public void render() {
		Graphics2D g = null;
		try{
			g = Display.getGraphics();
			clearScreen(g);
			gsm.render(g);
		}finally{ g.dispose();}
		Display.update();
	}
	
	private void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
