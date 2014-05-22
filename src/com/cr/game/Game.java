package com.cr.game;

import com.cr.graphics.Screen;
import com.cr.input.KeyInput;

public class Game extends Core{
	
	private Screen screen;
	
	public Game(){
		screen = new Screen(WIDTH, HEIGHT);
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
		screen.render();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
