package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.game.GameStateManager;

public class PauseState extends GameState{

	public PauseState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public void tick(float dt) {
//		if(KeyInput.enter) {
//			if(gsm.next() instanceof PlayState){
//				PlayState ps = (PlayState) gsm.next();
//				ps.bg = false;
//			}
//			gsm.pop();
//		}
//		if(KeyInput.c) {
//			gsm.pop();
//			EntityManager.clear();
//			gsm.pop();
//		}
	}

	@Override
	public void render(Screen screen) {
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

}
