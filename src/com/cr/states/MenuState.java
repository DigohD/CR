package com.cr.states;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.GameStateManager;

public class MenuState extends GameState{

	Font font;
	public MenuState(GameStateManager gsm) {
		super(gsm);
		font = new Font("HELLO", new Vector3f(1.0f,1.0f,1.0f));
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.ENTER)){
			gsm.push(new PlayState(gsm));
		}
	}

	@Override
	public void render(Screen screen) {
		screen.renderFont(font, Window.getWidth()/2 - 100, Window.getHeight()/2 - 100, 0.3f);
	}

	

}
