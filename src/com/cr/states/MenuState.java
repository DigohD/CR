package com.cr.states;

import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.net.NetStatus;
import com.cr.states.net.MPClientState;
import com.cr.states.net.MPHostState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class MenuState extends GameState{

	public MenuState(GameStateManager gsm) {
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
		if(Input.getKey(Input.H)){
			NetStatus.isHOST = true;
			NetStatus.isMultiPlayer = true;
			gsm.push(new MPHostState(gsm));
		}
		
		if(Input.getKey(Input.J)){
			NetStatus.isHOST = false;
			NetStatus.isMultiPlayer = true;
			gsm.push(new MPClientState(gsm));
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
		FontLoader.releaseFont(f);
	}

	

}
