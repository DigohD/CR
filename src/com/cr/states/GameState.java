package com.cr.states;

import java.awt.Graphics2D;

public abstract class GameState {
	
	public boolean blockTicking;
	public boolean blockRendering;
	
	public abstract void tick(float dt);
	public abstract void render(Graphics2D g);

}
