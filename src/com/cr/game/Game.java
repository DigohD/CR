package com.cr.game;

import com.cr.engine.core.CoreEngine;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.states.PlayState;
import com.cr.util.ImageLoader;
import com.cr.world.World;

public class Game extends CoreEngine{
	
	private GameStateManager gsm;
	private Screen screen;
	
	public Game(){
		boolean fullscreen = true;
		Window.createWindow(1200, 675, fullscreen);
		init();
	}
	
	private void init(){
		new ImageLoader();
		screen = new Screen();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));
	}

	@Override
	public void getInput() {
		Input.tick();
		if(Input.getKey(Input.ESCAPE))
			stop();
	}

	@Override
	public void tick(float dt) {
		gsm.tick(dt);
	}

	@Override
	public void render() {
		screen.render(gsm);
	}
	
	@Override
	public void cleanUp() {
		World.getShader().deleteShader();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
