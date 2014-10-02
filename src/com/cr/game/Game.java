package com.cr.game;

import com.cr.engine.core.CoreEngine;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.engine.input.Input;
import com.cr.states.MenuState;
import com.cr.states.net.MPClientState;
import com.cr.states.net.MPHostState;
import com.cr.util.FontLoader;
import com.cr.util.ImageLoader;
import com.cr.world.World;

public class Game extends CoreEngine{
	
	private GameStateManager gsm;
	private Screen screen;
	public static Shader shader;
	
	public Game(){
		boolean fullScreen = true;
		if(fullScreen){
			Window.setFullScreen();
		}else Window.createWindow(800, 600, false);
	
		init();
	}
	
	private void init(){
		screen = new Screen();
		new ImageLoader();
	
		shader = new Shader("basicVertShader", "basicFragShader");
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		new FontLoader();
		
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
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
		shader.deleteShader();
		if(World.getShader() != null)
			World.getShader().deleteShader();
		
		if(MPHostState.getServer() != null)
			MPHostState.close();
		if(MPClientState.getClient() != null)
			MPClientState.close();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
