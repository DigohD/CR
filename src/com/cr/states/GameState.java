package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.game.GameStateManager;

public abstract class GameState {
	
	public static GameStateManager gsm;
	protected boolean blockTicking = true;
	protected boolean blockRendering = true;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void tick(float dt);
	public abstract void render(Screen screen);
	
	public boolean isTickingBlocked() {
		return blockTicking;
	}
	public boolean isRenderingBlocked() {
		return blockRendering;
	}
	public void setTickingBlocked(boolean blockTicking) {
		this.blockTicking = blockTicking;
	}
	public void setRenderingBlocked(boolean blockRendering) {
		this.blockRendering = blockRendering;
	}

}
