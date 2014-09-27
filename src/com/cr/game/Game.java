package com.cr.game;

import com.cr.crafting.v2.test.CraftTest;
import com.cr.engine.core.CoreEngine;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.engine.input.Input;
import com.cr.states.MenuState;
import com.cr.util.FontLoader;
import com.cr.util.ImageLoader;

public class Game extends CoreEngine{
	
	private GameStateManager gsm;
	private Screen screen;
	public static Shader shader;
	
	public Game(){
		boolean fullscreen = true;
		Window.createWindow(1200, 675, fullscreen);
		init();
	}
	
	private void init(){
		screen = new Screen();
		new ImageLoader();
		new FontLoader();
		
		shader = new Shader("basicVertShader", "basicFragShader");
		shader.addUniform("transformation");
		shader.addUniform("envMap");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void getInput() {
		Input.tick();
		if(Input.getKey(Input.ESCAPE))
			stop();
		
//		if(Input.getMouse(0))
//			System.out.println("mouse btn 0");
//		if(Input.getMouse(1))
//			System.out.println("mouse btn 1");
//		if(Input.getMouse(2))
//			System.out.println("mouse btn 2");
//		if(Input.getMouse(3))
//			System.out.println("mouse btn 3");
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
		
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
