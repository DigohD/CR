package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.item.weapon.Knife;

public class Inventory implements Tickable, Renderable{

	private InventorySlot[][] inventory = new InventorySlot[11][3];
	private InventoryButton b1, b2, b3;
	private Contour contour;
	
	private RightHandSlot rHSlot;
	private LeftHandSlot lHSlot;
	
	public Inventory(){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j] = new InventorySlot(i, j);
		
		inventory[0][0].setItem(new Knife());
		inventory[1][0].setItem(new Knife());
		inventory[2][0].setItem(new Knife());
		
		b1 = new InventoryButton(600, 430);
		b2 = new InventoryButton(600, 482);
		b3 = new InventoryButton(600, 534);
		
		contour = new Contour();
		
		rHSlot = new RightHandSlot();
		lHSlot = new LeftHandSlot();
	}
	
	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].render(g);
		
		b1.render(g);
		b2.render(g);
		b3.render(g);
		
		contour.render(g);
		
		rHSlot.render(g);
		lHSlot.render(g);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		
	}

	public RightHandSlot getrHSlot() {
		return rHSlot;
	}

	public LeftHandSlot getlHSlot() {
		return lHSlot;
	}

	
	
	
}
