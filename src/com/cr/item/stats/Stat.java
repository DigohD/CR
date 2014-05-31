package com.cr.item.stats;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.entity.Renderable;

public abstract class Stat{
	
	protected String name;
	protected Color color;
	protected boolean duplicate;
	
	public Stat(String name, Color color){
		this.name = name;
		this.color = color;
	}
	
	public abstract void applyToSheet();
	
	public abstract void render(Graphics2D g, int xPos, int yPos);

	public abstract void addAmount(float amount);
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
