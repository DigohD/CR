package com.cr.entity.hero.materials;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.inventory.Button;
import com.cr.input.Mouse;
import com.cr.item.Item;
import com.cr.resource.ImageLoader;

public abstract class MaterialSlot extends Button implements Renderable, Tickable{

	protected BufferedImage slotImage;
	protected BufferedImage materialImage;
	protected int xPos, yPos;
	protected int amount;
	
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
		g.setColor(Color.WHITE);
		
		g.drawString("" + amount, xPos + 5, yPos + 45);
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
	
	@Override
	public void clicked(){
		Materials.buttonClicked(this);
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public abstract void setMaterialImage(String imageName);
	
	
}
