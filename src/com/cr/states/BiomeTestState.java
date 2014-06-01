package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.inventory.InventoryButton;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.world.biome.Grasslands;

public class BiomeTestState extends GameState{

	private BufferedImage bg = ImageLoader.getImage("inventorybg");
	private BufferedImage test, test2, test3, finalWorld;
	private ExitButton exit;
	private Grasslands grasslands;
	
	public BiomeTestState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		grasslands = new Grasslands();
		test = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		finalWorld = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		
		int pixels[] = grasslands.getBottomLayer().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		pixels = grasslands.getMiddleLayer().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test2.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		pixels = grasslands.getTopLayer().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test3.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		exit.tick(dt);

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
		
		g.drawImage(test,
				
				xOffset,
				yOffset,
				xOffset + 300,
				yOffset + 300,
				
				0,
				0,
				100,
				100,
				
				null);
		
		g.drawImage(test2,
				
				xOffset + 300,
				yOffset,
				xOffset + 600,
				yOffset + 300,
				
				0,
				0,
				100,
				100,
				
				null);
		
		g.drawImage(test3,
				
				xOffset,
				yOffset + 300,
				xOffset + 300,
				yOffset + 600,
				
				0,
				0,
				100,
				100,
				
				null);
		
		g.drawImage(finalWorld,
				
				xOffset + 300,
				yOffset + 300,
				xOffset + 600,
				yOffset + 600,
				
				0,
				0,
				100,
				100,
				
				null);
		
		exit.render(g);
		
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

}
