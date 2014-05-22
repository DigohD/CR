package com.cr.game;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.input.KeyInput;

public class Game extends Core{
	
	public Game(){
		
	}

	@Override
	public void getInput() {
		KeyInput.tick();
		if(KeyInput.esc){
			Display.restoreScreen();
			System.exit(0);
		}		
	}

	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void render() {
		Graphics2D g = null;
		try{
			g = Display.getGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
			
		}finally{ g.dispose();}
		Display.update();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
