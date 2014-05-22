package com.cr.game;

import com.cr.graphics.Screen;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;

public class Game extends Core{
	
	private Screen screen;
	
	public Game(){
		new ImageLoader();
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
