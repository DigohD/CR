package com.cr.entity.hero.materials;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.crafting.material.Material;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.Game;
import com.cr.input.Mouse;
import com.cr.item.Item;
import com.cr.resource.ImageLoader;

public abstract class MaterialSlot extends Button implements Renderable, Tickable{

	protected BufferedImage slotImage;
	protected BufferedImage materialImage;
	protected int xPos, yPos;
	protected int amount;
	protected String name;
	protected boolean isClicked;
	
	public MaterialSlot(int xPos, int yPos){
		super(new Rectangle(xPos, yPos, 50, 50));
		slotImage = ImageLoader.getImage("slot");
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(slotImage, xPos, yPos, null);
		g.drawImage(materialImage, xPos, yPos, null);
		
		Font font = new Font("Tahoma", 14, 14);
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		g.drawString("" + amount, xPos + 4, yPos + 44);
		g.setColor(Color.WHITE);
		g.drawString("" + amount, xPos + 5, yPos + 45);
		
		font = new Font("Tahoma", 18, 18);
		g.setFont(font);
		
		int xOff = ((Game.WIDTH - 800) / 2);
		int yOff = ((Game.HEIGHT - 600) / 2);
		if(rect.contains(Mouse.getX(), Mouse.getY()))
			g.drawString(name, xOff + 600, yOff + 30);
		
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
	@Override
	public void clicked(){
		Materials.buttonClicked(this);
		isClicked = true;
	}
	
	public int getAmount() {
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
