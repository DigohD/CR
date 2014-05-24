package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;

public class Inventory implements Tickable, Renderable{

	private InventorySlot[][] inventory = new InventorySlot[11][3];
	
	public Inventory(){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j] = new InventorySlot(i, j);
	}
	
	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].render(g);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		
	}

	
	
}
