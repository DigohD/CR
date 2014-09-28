package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.item.Item;

public class Inventory implements Tickable, Renderable{

	private static InventorySlot[][] inventory = new InventorySlot[11][3];
	private Contour contour;
	
	private RightHandSlot rHSlot;
	private LeftHandSlot lHSlot;
	private HeadSlot headSlot;
	private UpperBodySlot upperBodySlot;
	
	private static Item selectedItem;
	
	public Inventory(){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j] = new InventorySlot(i, j, xOffset, yOffset);
		
		inactivateSlots();
		
		contour = new Contour(xOffset, yOffset);
		
		selectedItem = null;
		
		rHSlot = new RightHandSlot(xOffset, yOffset);
		lHSlot = new LeftHandSlot(xOffset, yOffset);
		headSlot = new HeadSlot(xOffset, yOffset);
		upperBodySlot = new UpperBodySlot(xOffset, yOffset);
	}
	
	@Override
	public void render(Screen screen) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].render(screen);
		
//		b1.render(g);
//		b2.render(g);
//		b3.render(g);
		
		contour.render(screen);
		
		rHSlot.render(screen);
		lHSlot.render(screen);
		headSlot.render(screen);
		upperBodySlot.render(screen);
	}

	@Override
	public Sprite getSprite() {
		return null;
	}

	public void activateSlots(){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].activateItemSlot();
	}
	
	public void inactivateSlots(){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].inactivateItemSlot();
	}
	
	
	@Override
	public void tick(float dt) {
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				inventory[i][j].tick(dt);;
				
		lHSlot.tick(dt);
		rHSlot.tick(dt);
		headSlot.tick(dt);
		upperBodySlot.tick(dt);
	}

	public static void buttonClicked(Button button){
		if(button instanceof ItemSlot){
			ItemSlot is = (ItemSlot) button;
			if(selectedItem != null && is.isCompatible(selectedItem) && 
					is.getItem() == null){
				is.setItem(selectedItem);
				
				if(is instanceof LeftHandSlot ||
						is instanceof RightHandSlot ||
						is instanceof HeadSlot ||
						is instanceof UpperBodySlot)
					is.applyStats();
				
				selectedItem = null;
			}else if(selectedItem == null && is.getItem() != null){
				if(is instanceof LeftHandSlot ||
						is instanceof RightHandSlot ||
						is instanceof HeadSlot ||
						is instanceof UpperBodySlot)
					is.unApplyStats();
				
				selectedItem = is.getItem();
				is.setItem(null);
			}
		}
	}
	
	public static void addItem(Item item){
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 3; j++)
				if(inventory[i][j].getItem() == null){
					inventory[i][j].setItem(item);
					return;
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
	
	public UpperBodySlot getUpperBodySlot() {
		return upperBodySlot;
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	
	
	
}
