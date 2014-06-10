package com.cr.entity.hero.materials;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.crafting.material.Material;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;

public abstract class MaterialSlot extends Button implements Renderable, Tickable{

	protected Sprite slotSprite;
	protected Sprite materialSprite;
	protected int xPos, yPos;
	protected int amount;
	protected String name;
	protected boolean isClicked;
	
	public MaterialSlot(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 50, 50));
		slotSprite = new Sprite("slot");
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public void render(Screen screen){
		screen.renderSprite(slotSprite, xPos, yPos);
		screen.renderSprite(materialSprite, xPos, yPos);
		
//		Font font = new Font("Tahoma", 14, 14);
//		g.setFont(font);
//		
//		g.setColor(Color.BLACK);
//		g.drawString("" + amount, xPos + 4, yPos + 44);
//		g.setColor(Color.WHITE);
//		g.drawString("" + amount, xPos + 5, yPos + 45);
//		
//		font = new Font("Tahoma", 18, 18);
//		g.setFont(font);
	}

	public void renderHoover(Screen screen){
//		if(rect.contains(Mouse.getX(), Mouse.getY())){
//			g.setColor(Color.BLACK);
//			g.fillRect(Mouse.getX(), Mouse.getY(), 20 + (name.length() * 9), 20);
//			g.setColor(Color.WHITE);
//			g.drawString(name, Mouse.getX() + 18, Mouse.getY() + 16);
//		}
	}
	
	@Override
	public Sprite getSprite(){
		return null;
	}
	
	@Override
	public void clicked(){
		Materials.buttonClicked(this);
		isClicked = true;
	}
	
	public int getAmount(){
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public abstract void setMaterialImage(String imageName);
	
	public boolean isClicked() {
		if(isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}
	
	public abstract Material getMaterial();
}
