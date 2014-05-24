package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;

public class Inventory implements Tickable, Renderable{

	private InventorySlot[][] inventory = new InventorySlot[11][3];
	private InventoryButton b1, b2, b3;
	
	public Inventory(){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j] = new InventorySlot(i, j);
		
		b1 = new InventoryButton(600, 430);
		b2 = new InventoryButton(600, 482);
		b3 = new InventoryButton(600, 534);
	}
	
	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].render(g);
		
		b1.render(g);
		b2.render(g);
		b3.render(g);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		
	}

	
	
}
