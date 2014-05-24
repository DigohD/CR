package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;

public class InventoryState extends GameState{

	private BufferedImage bg = ImageLoader.getImage("inventorybg");
	private Inventory inventory;
	
	public InventoryState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		inventory = Hero.getInventory();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		if(KeyInput.esc) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
				ps.bg = false;
			}
			gsm.pop();
		}
		
//		if(KeyInput.enter) {
//			if(gsm.next() instanceof PlayState){
//				PlayState ps = (PlayState) gsm.next();
//				ps.bg = false;
//			}
//			gsm.pop();
//		}
//		if(KeyInput.c) {
//			gsm.pop();
//			EntityManager.clear();
//			gsm.pop();
//		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		inventory.render(g);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

}
