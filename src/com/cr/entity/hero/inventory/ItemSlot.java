package com.cr.entity.hero.inventory;

import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.game.Game;
import com.cr.input.Mouse;
import com.cr.item.Item;
import com.cr.stats.StatMod;

public abstract class ItemSlot extends Button implements Renderable, Tickable, Hooverable{

	protected Sprite slotSprite;
	protected Sprite itemSprite;
	protected int xPos, yPos;
	
	protected boolean isHoover, isStatsApplied;
	
	protected Item item;
	
	public ItemSlot(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 50, 50));
		slotSprite = new Sprite("slot", Game.shader, new Transform());
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
		if(isHoover && item != null)
			renderHoover(screen);
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
	
	public void inactivateItemSlot(){
		Input.removeButton(this);
	}
	
	public void activateItemSlot(){
		Input.addButton(this);
	}
	
	public abstract boolean isCompatible(Item item);

	public void applyStats(){
		for(StatMod x : item.getStatMods().getStatMods())
			Hero.getHeroSheet().addMod(x);
		isStatsApplied = true;
	}
	
	public void unApplyStats(){
		for(StatMod x : item.getStatMods().getStatMods())
			Hero.getHeroSheet().removeMod(x);
		isStatsApplied = false;
	}
	
	@Override
	public void renderHoover(Screen screen) {
		item.renderDescription(screen);
	}

	@Override
	public void setHoover(boolean bool) {
		isHoover = bool;
	}

	@Override
	public boolean isHoover() {
		return isHoover;
	}
	
	
}
