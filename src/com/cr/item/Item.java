package com.cr.item;

import java.util.ArrayList;
import java.util.HashMap;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.Hero.Direction;
import com.cr.game.Game;
import com.cr.item.weapon.Weapon;
import com.cr.stats.StatMod;
import com.cr.stats.StatModList;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.CRString;
import com.cr.util.Camera;
import com.cr.util.FontLoader;
import com.cr.world.World;

public abstract class Item implements Renderable, Tickable{
	
	protected Sprite sprite;
	protected Sprite iconSprite;
	
	protected int xOffset, yOffset;
	protected int vertXOffset, horXOffset;
	protected int tempXOffset, tempYOffset;
	
	protected String name;
	protected int width, height;
	
	protected StatModList stats;
	
	public int x0, x1, y0, y1;
	
	protected Vector2f pos;
	
	public Item(String imageString, int horXOffset, int vertXOffset, int xOffset, int yOffset, String name){
		sprite = new Sprite(imageString, 1, 4, 0, 0, World.getShader(), Hero.t);
		iconSprite = new Sprite(imageString + "icon", Game.shader, new Transform());
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.horXOffset = horXOffset;
		this.vertXOffset = vertXOffset;
		
		width = sprite.getSpriteWidth() / 4;
		height = sprite.getSpriteHeight();
		
		this.name = name;
		
		stats = new StatModList();
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
		x0 = drawX + xOffset + horXOffset + tempXOffset;
		x1 = drawY + yOffset + tempYOffset;
		y0 = drawX + width + xOffset + horXOffset + tempXOffset;
		y1 = drawY + height + yOffset + tempYOffset;
		
		screen.renderSprite(sprite, x0, x1, 0, spriteID);
	}
	
	public void renderDescription(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		
		f.setFont(CRString.create(name));
		f.renderFont(xOffset + 12, yOffset - 20, 0.3f);
		
		int counter = 0;
		if(this instanceof Weapon){
			Weapon w = (Weapon) this;
			
			String amount = w.getDamageBase().getTotal() + "";
			String amount2 = w.getDamageDice().getTotal() + w.getDamageBase().getTotal() + "";
			String s = CRString.create("Damage " + amount.substring(0, 1) + " - " + amount2.substring(0, 1));
			f.setFont(s);
			f.renderFont(xOffset + 12, yOffset + 24, 0.3f);
			
			amount = w.getCooldown().getTotal()/60 + "";
			s = CRString.create("Cooldown " + amount.substring(0, 3));
			f.setFont(s);
			f.renderFont(xOffset + 12, yOffset + 52, 0.3f);
			counter = 3;
		}
		
		for(StatMod x : stats.getStatMods()){
			String amount = x.getAmount() + "";
			int end = amount.lastIndexOf('.');
			String s = CRString.create(StatsSheet.getStatString(x.getAffectedStat()) + " " + amount.substring(0, end));
			f.setFont(s);
			f.renderFont(xOffset + 12, yOffset + 24 + (counter * 28), 0.3f);
			counter++;
		}
		
//		Font font = new Font("Tahoma", 18, 18);
//		g.setFont(font);
//		g.setColor(Color.WHITE);
//		g.drawString(name, xOffset + 20, yOffset + 40);
		
//		stats.render(screen, xOffset + 20, yOffset + 80);
		
		FontLoader.releaseFont(f);
	}
	
	public void statsInit(){
		ArrayList<StatMod> tmp = stats.getStatMods();
		HashMap<StatID, StatMod> newMap = new HashMap<StatID, StatMod>();
		
		for(StatMod x : tmp){
			if(!newMap.containsKey(x.getAffectedStat()))
				newMap.put(x.getAffectedStat(), x);
			else
				newMap.get(x.getAffectedStat()).addAmount(x.getAmount());
		}
		
		stats = new StatModList();
		
		for(StatMod x : newMap.values())
			stats.addMod(x);
	}
	
	protected void tickPassives(float dt){
		
	}
	
	public StatModList getStatMods(){
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
	
	public void addStat(StatMod mod){
		stats.addMod(mod);
	}

	public int getTempXOffset() {
		return tempXOffset;
	}

	public int getTempYOffset() {
		return tempYOffset;
	}

	public Vector2f getPos() {
		int x = x0;
		int y = x1;
		return new Vector2f(x, y);
	}
	
	public abstract void activate();
	
	

}
