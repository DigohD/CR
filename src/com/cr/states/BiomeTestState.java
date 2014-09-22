package com.cr.states;

import java.awt.image.BufferedImage;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.game.GameStateManager;
import com.cr.world.biome.Grasslands;

public class BiomeTestState extends GameState{

	//private BufferedImage bg = ImageLoaderOld.getImage("inventorybg");
	private BufferedImage test, test2, test3, finalWorld;
	private ExitButton exit;
	private Grasslands grasslands;
	
	public BiomeTestState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		grasslands = new Grasslands(100, 100);
		test = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		finalWorld = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		
		int pixels[] = grasslands.getBottomLayer().getBitmap().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		pixels = grasslands.getMiddleLayer().getBitmap().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test2.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		pixels = grasslands.getTopLayer().getBitmap().getPixels();
		
		for(int i = 0; i < pixels.length; i++){
			test3.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
			if(pixels[(i % 100) + ((i / 100) * 100)] != 0)
				finalWorld.setRGB(i % 100, i / 100, pixels[(i % 100) + ((i / 100) * 100)]);
		}
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		exit.tick(dt);

		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
		
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
	public void render(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
//		g.drawImage(bg, xOffset, yOffset, null);
//		
//		g.drawImage(test,
//				
//				xOffset,
//				yOffset,
//				xOffset + 300,
//				yOffset + 300,
//				
//				0,
//				0,
//				100,
//				100,
//				
//				null);
//		
//		g.drawImage(test2,
//				
//				xOffset + 300,
//				yOffset,
//				xOffset + 600,
//				yOffset + 300,
//				
//				0,
//				0,
//				100,
//				100,
//				
//				null);
//		
//		g.drawImage(test3,
//				
//				xOffset,
//				yOffset + 300,
//				xOffset + 300,
//				yOffset + 600,
//				
//				0,
//				0,
//				100,
//				100,
//				
//				null);
//		
//		g.drawImage(finalWorld,
//				
//				xOffset + 300,
//				yOffset + 300,
//				xOffset + 600,
//				yOffset + 600,
//				
//				0,
//				0,
//				100,
//				100,
//				
//				null);
//		
//		exit.render(g);
		
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}

}
