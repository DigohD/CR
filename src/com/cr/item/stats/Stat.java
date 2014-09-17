package com.cr.item.stats;

import java.awt.Color;

import com.cr.engine.graphics.Screen;

public abstract class Stat{
	
	protected String name;
	protected Color color;
	protected boolean duplicate;
	
	public Stat(String name, Color color){
		this.name = name;
		this.color = color;
	}
	
	public abstract void applyToSheet();
	
	public abstract void render(Screen screen, int xPos, int yPos);

	public abstract void addAmount(float amount);
	public abstract void modAmount(float amount);
	public abstract float getAmount();
	
	public String getName() {
		return name;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

	
	
	
	
}
