package com.cr.entity.hero.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.crafting.material.Copper;
import com.cr.crafting.material.Pyrite;
import com.cr.crafting.material.Quartz;
import com.cr.crafting.weapon.KnifePattern;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.Display;
import com.cr.game.Game;
import com.cr.item.Item;
import com.cr.item.armor.head.CopperHelm;
import com.cr.item.weapon.Hammer;
import com.cr.item.weapon.CopperKnife;

public class Inventory implements Tickable, Renderable{

	private InventorySlot[][] inventory = new InventorySlot[11][3];
	private InventoryButton b1, b2, b3;
	private Contour contour;
	
	private RightHandSlot rHSlot;
	private LeftHandSlot lHSlot;
	private HeadSlot headSlot;
	
	private static Item selectedItem;
	
	public Inventory(){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j] = new InventorySlot(i, j, xOffset, yOffset);
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 20);
		inventory[1][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 40);
		inventory[2][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 60);
		inventory[3][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 80);
		inventory[4][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 20);
		KnifePattern.applyMaterial(new Pyrite(), 3);
		KnifePattern.applyMaterial(new Quartz(), 4);
		inventory[6][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 40);
		KnifePattern.applyMaterial(new Pyrite(), 5);
		KnifePattern.applyMaterial(new Quartz(), 7);
		inventory[7][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 60);
		KnifePattern.applyMaterial(new Pyrite(), 6);
		KnifePattern.applyMaterial(new Quartz(), 9);
		inventory[8][0].setItem(KnifePattern.getKnife());
		
		KnifePattern.startNew();
		KnifePattern.applyBaseMaterial(new Copper(), 80);
		KnifePattern.applyMaterial(new Pyrite(), 8);
		KnifePattern.applyMaterial(new Quartz(), 11);
		inventory[9][0].setItem(KnifePattern.getKnife());
		
//		b1 = new InventoryButton(600 + xOffset, 430 + yOffset);
//		b2 = new InventoryButton(600 + xOffset, 482 + yOffset);
//		b3 = new InventoryButton(600 + xOffset, 534 + yOffset);
		
		contour = new Contour(xOffset, yOffset);
		
		selectedItem = null;
		
		rHSlot = new RightHandSlot(xOffset, yOffset);
		lHSlot = new LeftHandSlot(xOffset, yOffset);
		headSlot = new HeadSlot(xOffset, yOffset);
	}
	
	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].render(g);
		
//		b1.render(g);
//		b2.render(g);
//		b3.render(g);
		
		contour.render(g);
		
		rHSlot.render(g);
		lHSlot.render(g);
		headSlot.render(g);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public void tick(float dt) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].tick(dt);;
				
		lHSlot.tick(dt);
		rHSlot.tick(dt);
		headSlot.tick(dt);
	}

	public static void buttonClicked(Button button){
		if(button instanceof ItemSlot){
			ItemSlot is = (ItemSlot) button;
			if(selectedItem != null && is.isCompatible(selectedItem) && 
					is.getItem() == null){
				is.setItem(selectedItem);
				Display.standardCursor();
				selectedItem = null;
			}else if(selectedItem == null && is.getItem() != null){
				selectedItem = is.getItem();
				Display.setCursor(selectedItem.getIconImage());
				is.setItem(null);
			}
		}
	}
	
	public RightHandSlot getrHSlot() {
		return rHSlot;
	}

	public LeftHandSlot getlHSlot() {
		return lHSlot;
	}
	
	public HeadSlot getHeadSlot() {
		return headSlot;
	}

	
	
	
}
