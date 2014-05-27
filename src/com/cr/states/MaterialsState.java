package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.inventory.InventoryButton;
import com.cr.entity.hero.materials.BaseButton;
import com.cr.entity.hero.materials.EssencesButton;
import com.cr.entity.hero.materials.MineralsButton;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;

public class MaterialsState extends GameState{

	private BufferedImage bg = ImageLoader.getImage("inventorybg");
	private ExitButton exit;
	private BaseButton base;
	private EssencesButton essences;
	private MineralsButton minerals;
	
	public MaterialsState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		base = new BaseButton(600 + xOffset, 378 + yOffset);
		essences = new EssencesButton(600 + xOffset, 430 + yOffset);
		minerals = new MineralsButton(600 + xOffset, 482 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
//		inventory.tick(dt);
		
		base.tick(dt);
		essences.tick(dt);
		minerals.tick(dt);
		exit.tick(dt);
		
//		Hero.updateInventory();
		
		if(KeyInput.space || exit.isClicked()) {
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
	public void render(Graphics2D g){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		g.drawImage(bg, xOffset, yOffset, null);
//		inventory.render(g);
		base.render(g);
		essences.render(g);
		minerals.render(g);
		exit.render(g);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

}
