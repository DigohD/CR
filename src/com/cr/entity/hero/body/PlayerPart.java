package com.cr.entity.hero.body;

import java.awt.Rectangle;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.anim.Bob;
import com.cr.item.Item;
import com.cr.world.World;

public abstract class PlayerPart implements Renderable, Tickable{

	protected Sprite sprite;
	protected Bob bob;
	
	protected Item item;
	
	protected int width, height;
	protected int horXOffset, vertXOffset, xOffset, yOffset;
	
	public PlayerPart(String imageString, Bob bob, int horXOffset, int vertXOffset, int xOffset, int yOffset){
		sprite = new Sprite(imageString, 1, 4, 0, 0, World.getShader(), Hero.t);
		
		width = sprite.getSpriteWidth();
		height = sprite.getSpriteHeight();
		
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.bob = bob;
	}
	
	@Override
	public void render(Screen screen){
		int x = (int) Hero.position.x;
		int y = (int) Hero.position.y;
		
		Direction dir = Hero.currentDir;
		int spriteID = 0;
		int horXOffset = 0;
		
		switch(dir){
			case SOUTH:
				spriteID = 0;
				horXOffset = this.vertXOffset;
				break;
			case EAST:
				spriteID = 1;
				horXOffset = this.horXOffset;
				break;
			case NORTH:
				spriteID = 2;
				horXOffset = -this.vertXOffset;
				break;
			case WEST:
				spriteID = 3;
				horXOffset = -this.horXOffset;
				break;
		}
		
		int drawX = x + (int)bob.getOffset().x + horXOffset + xOffset;
		int drawY = y + (int)bob.getOffset().y + yOffset;
		
		if(item != null && item.renderPrePart(dir))
			item.render(screen, drawX, drawY, spriteID);
		
		if(item != null)
			screen.renderSprite(sprite, drawX + item.getTempXOffset(), drawY + item.getTempYOffset(), 0, spriteID);
		else
			screen.renderSprite(sprite, drawX,drawY, 0, spriteID);
		
		
		if(item != null && !item.renderPrePart(dir))
			item.render(screen, drawX, drawY, spriteID);
	}
	
	@Override
	public void tick(float dt) {
		bob.tick(dt);
		if(item != null)
			item.tick(dt);
	}
	
	@Override
	public Rectangle getRect() {
		int x = (int) Hero.position.x;
		int y = (int) Hero.position.y;
		
		int drawX = x + (int)bob.getOffset().x + horXOffset + xOffset;
		int drawY = y + (int)bob.getOffset().y + yOffset;
		
		return new Rectangle((int) drawX, (int) drawY, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public Bob getBob() {
		return bob;
	}
	
	public void setItem(Item item){
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
	
	
}
