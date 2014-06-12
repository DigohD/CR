package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.input.Mouse;
import com.cr.item.Item;

public abstract class ItemSlot extends Button implements Renderable, Tickable{

	protected Sprite slotSprite;
	protected Sprite itemSprite;
	protected int xPos, yPos;
	
	protected Item item;
	
	public ItemSlot(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 50, 50));
		slotSprite = new Sprite("slot");
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderStaticSprite(slotSprite, xPos, yPos);
		if(item != null)
			screen.renderStaticSprite(item.getIconSprite(), xPos, yPos);
		if(item != null && rect.contains(Mouse.getX(), Mouse.getY()))
			item.renderDescription(screen);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@Override
	public void clicked(){
		Inventory.buttonClicked(this);
	}
	
	public abstract boolean isCompatible(Item item);

	
}
