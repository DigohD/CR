package com.cr.states.menus;

import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.net.NetStatus;
import com.cr.states.BiomeTestState;
import com.cr.states.GameState;
import com.cr.states.PlayState;
import com.cr.states.TestState;
import com.cr.states.net.ClientInputState;
import com.cr.states.net.ServerInputState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class StartMenuState extends GameState{

	public StartMenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.ENTER)){
			gsm.push(new PlayState(gsm));
		}
		
		if(Input.getKey(Input.B)){
			gsm.push(new BiomeTestState(gsm));
		}
		
		if(Input.getKey(Input.T)){
			gsm.push(new TestState(gsm));
		}
		
		if(Input.getKey(Input.H)){
			gsm.push(new ServerInputState(gsm));
		}
		
		if(Input.getKey(Input.J)){
			gsm.push(new ClientInputState(gsm));
		}
		
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
	}

	@Override
	public void render(Screen screen) {
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		f.setFont(CRString.create("Press Enter to Play!"));
		screen.renderFont(f, Window.getWidth()/2 - 200, Window.getHeight()/2 - 100, 0.5f);
		
		f.setFont(CRString.create("Press H to Host"));
		screen.renderFont(f, Window.getWidth()/2 - 200, Window.getHeight()/2 - 100 + 40, 0.5f);
		
		f.setFont(CRString.create("Press J to Join"));
		screen.renderFont(f, Window.getWidth()/2 - 200, Window.getHeight()/2 - 100 + 80, 0.5f);
		
		f.setFont(CRString.create("Press B to see biome"));
		screen.renderFont(f, Window.getWidth()/2 - 200, Window.getHeight()/2 - 100 + 80 + 40, 0.5f);
		
		f.setFont(CRString.create("Press T to enter test state"));
		screen.renderFont(f, Window.getWidth()/2 - 200, Window.getHeight()/2 - 100 + 80 + 80, 0.5f);
		FontLoader.releaseFont(f);
	}

	

}
