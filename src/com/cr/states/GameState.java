package com.cr.states;

import java.awt.Graphics2D;

import com.cr.game.GameStateManager;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected boolean blockTicking = true;
	protected boolean blockRendering = true;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void tick(float dt);
	public abstract void render(Graphics2D g);
	
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
