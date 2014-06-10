package com.cr.item;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.item.stats.PassiveTicking;
import com.cr.item.stats.Stat;
import com.cr.item.stats.StatsList;
import com.cr.util.Camera;

public abstract class Item implements Renderable, Tickable{
	
	protected Sprite sprite;
	protected Sprite iconSprite;
	
	protected int xOffset, yOffset;
	protected int vertXOffset, horXOffset;
	protected int tempXOffset, tempYOffset;
	
	protected String name;
	protected int width, height;
	
	protected StatsList stats;
	
	public int x0, x1, y0, y1;
	
	protected Vector2f pos;
	
	public Item(String imageString, int horXOffset, int vertXOffset, int xOffset, int yOffset, String name){
		sprite = new Sprite(imageString);
		iconSprite = new Sprite(imageString + "icon");
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		
		width = sprite.getWidth() / 4;
		height = sprite.getHeight();
		
		this.name = name;
		
		stats = new StatsList();
	}
	
	public void render(Screen screen, int drawX, int drawY, int spriteID){
		Direction dir = Hero.currentDir;
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
		x0 = drawX + xOffset + horXOffset + tempXOffset - (int) Camera.getCamX();
		x1 = drawY + yOffset + tempYOffset - (int) Camera.getCamY();
		y0 = drawX + width + xOffset + horXOffset + tempXOffset - (int) Camera.getCamX();
		y1 = drawY + height + yOffset + tempYOffset - (int) Camera.getCamY();
		
		screen.renderSprite(sprite, x0, x1, 0, spriteID);
	}
	
	public void renderDescription(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
//		Font font = new Font("Tahoma", 18, 18);
//		g.setFont(font);
//		g.setColor(Color.WHITE);
//		g.drawString(name, xOffset + 20, yOffset + 40);
		
		stats.render(screen, xOffset + 20, yOffset + 80);
	}
	
	protected void tickPassives(float dt){
		for(Stat s : stats.getStats())
			if(s instanceof PassiveTicking)
				((PassiveTicking) s).tick(dt);
	}
	
	public StatsList getStats(){
		return stats;
	}
	
	

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	public abstract boolean renderPrePart(Direction dir);

	public Sprite getIconSprite() {
		return iconSprite;
	}
	
	public void addStat(Stat stat){
		stats.addStat(stat);
	}

	public int getTempXOffset() {
		return tempXOffset;
	}

	public int getTempYOffset() {
		return tempYOffset;
	}

	public Vector2f getPos() {
		int x = x0 + (int) Camera.getCamX();
		int y = x1 + (int) Camera.getCamY();
		return new Vector2f(x, y);
	}
	
	public abstract void activate();
	
	

}
